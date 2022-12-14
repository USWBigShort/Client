package presentation;

import data.UserInfoSocket;
import data.repositoryimpl.CoinInfoRepositoryImpl;
import data.CoinInfoSocket;
import data.repositoryimpl.UserInfoRepositoryImpl;
import domain.repository.CoinInfoRepository;
import domain.repository.UserInfoRepository;
import domain.usecase.FetchCoinInfoUseCase;
import domain.usecase.FetchUserInfoUseCase;
import domain.usecase.PostUserInfoUseCase;

import java.io.IOException;
import java.net.*;

public class Presenter implements MainContract.Presenter {

    private static FetchUserInfoUseCase fetchUserInfoUseCase;
    private static PostUserInfoUseCase postUserInfoUseCase;

    public static void main(String[] args) throws IOException {
        View view = new View("USW BigShort");
        view.setVisible(true);

        CoinInfoSocket coinInfoSocket = CoinInfoSocket.getInstance();
        CoinInfoRepository coinInfoRepository = new CoinInfoRepositoryImpl(coinInfoSocket);
        FetchCoinInfoUseCase fetchCoinInfoUseCase = new FetchCoinInfoUseCase(coinInfoRepository);

        UserInfoSocket userInfoSocket = UserInfoSocket.getInstance();
        UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl(userInfoSocket);
        fetchUserInfoUseCase = new FetchUserInfoUseCase(userInfoRepository);
        postUserInfoUseCase = new PostUserInfoUseCase(userInfoRepository);

        Thread receiveBroadCastSocket = new Thread(() -> {
            while (true) {
                try {
                    String msgFromServer = fetchCoinInfoUseCase.execute();
                    System.out.println("Multicast -> " + msgFromServer);

                    if(msgFromServer.startsWith("UPDATE:")) {
                        String updateCoinInfo = StringUtil.CleanUpString(msgFromServer, "UPDATE: ".length());
                        String updateCoinName = updateCoinInfo.split(",")[0];
                        int updateCoinPrice = Integer.parseInt(updateCoinInfo.split(",")[1]);
                        float averagePurchasePrice = Float.parseFloat(view.getSelectedCoinAveragePurchasePriceValue());
                        if(updateCoinName.equals(view.getSelectedCoin()) && averagePurchasePrice != 0) {
                            String profit = Integer.toString(updateCoinPrice - (int)averagePurchasePrice);
                            view.updateProfit(profit);
                        }
                        int idx = 0;
                        for(int i = 0; i < view.coinModel.size(); i++) {
                            if(view.coinModel.get(i).toString().startsWith(updateCoinName)) {
                                idx = i;
                                break;
                            }
                        }
                        view.updateCoinInfo(updateCoinInfo, idx);
                    }

                    if(msgFromServer.startsWith("ADD:")) {
                        String updateCoinInfo = StringUtil.CleanUpString(msgFromServer, "ADD: ".length());
                        view.addCoin(updateCoinInfo);
                        String noticeText = view.getNoticeText();
                        noticeText += "\n?????? ?????? : " + updateCoinInfo;
                        view.updateNotice(noticeText);
                    }

                    if(msgFromServer.startsWith("REMOVE:")) {
                        String deleteCoinInfo = StringUtil.CleanUpString(msgFromServer, "REMOVE: ".length());

                        String deleteCoinName = deleteCoinInfo.split(",")[0];
                        int idx = 0;
                        for(int i = 0; i < view.coinModel.size(); i++) {
                            if(view.coinModel.get(i).toString().startsWith(deleteCoinName)) {
                                idx = i;
                                break;
                            }
                        }
                        view.deleteCoin(idx);
                        String noticeText = view.getNoticeText();
                        noticeText += "\n?????? ?????? : " + deleteCoinInfo;
                        view.updateNotice(noticeText);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveBroadCastSocket.start();

        Thread receiveTCPSocket = new Thread (() -> {
            while (true) {
                try {
                    String msgFromServer = fetchUserInfoUseCase.execute();
                    System.out.println("TCP -> " + msgFromServer);

                    if(msgFromServer.startsWith("?????? ??????")) {
                        view.setAvailableAssets(msgFromServer.substring("?????? ?????? ".length()));
                    }

                    if(msgFromServer.startsWith("?????? ??????")) {
                        view.setAvailableAssets(msgFromServer.substring("?????? ?????? : ".length()));
                    }

                    if (msgFromServer.startsWith("[")) {
                        view.addCoin(msgFromServer.replace("[","").replace("]",""));
                    }

                    if (msgFromServer.startsWith("HOLDING")) {
                        String[] msgFormServerList = StringUtil.CleanUpString(msgFromServer, "HOLDINGS: ".length())
                                .split(",");

                        view.setSelectedCoinInfo(msgFormServerList[1], msgFormServerList[2], msgFormServerList[3]);
                    }

                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    view.setSelectedCoinInfo("0","0","0");
                    e.printStackTrace();
                }
            }
        });

        receiveTCPSocket.start();
    }

    @Override
    public void buySelectedCoin(String selectedCoinAndAmount) throws IOException {
        postUserInfoUseCase.execute("?????? " + selectedCoinAndAmount);
    }

    @Override
    public void sellSelectedCoin(String selectedCoinAndAmount) throws IOException {
        postUserInfoUseCase.execute("?????? " + selectedCoinAndAmount);
    }

    @Override
    public void getSelectedCoinInfo(String selectedCoin) throws IOException {
        System.out.println("????????? ?????? ?????? : " + selectedCoin + ";");
        postUserInfoUseCase.execute(selectedCoin);
    }
}

