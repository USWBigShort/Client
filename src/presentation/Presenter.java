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
                    System.out.println(fetchCoinInfoUseCase.execute());
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
                    System.out.println(msgFromServer);

                    if(msgFromServer.startsWith("기본 비용")) {
                        view.setAvailableAssets(msgFromServer.substring("기본 비용 ".length()));
                    }

                    if(msgFromServer.startsWith("가용 자산")) {
                        view.setAvailableAssets(msgFromServer.substring("가용 자산 : ".length()));
                    }

                    if (msgFromServer.startsWith("[")) {
                        view.addCoin(msgFromServer.replace("[","").replace("]",""));
                    }

                    if (msgFromServer.startsWith("HOLDING")) {
                        String[] msgFormServerList = msgFromServer
                                .replaceAll(" ","")
                                .replace("[","")
                                .replace("]","")
                                .split(",");

                        view.setSelectedCoinInfo(msgFormServerList[1], msgFormServerList[2], msgFormServerList[3]);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveTCPSocket.start();
    }

    @Override
    public void buySelectedCoin(String selectedCoinAndAmount) throws IOException {
        postUserInfoUseCase.execute("매수 " + selectedCoinAndAmount);
    }

    @Override
    public void sellSelectedCoin(String selectedCoinAndAmount) throws IOException {
        postUserInfoUseCase.execute("매도 " + selectedCoinAndAmount);
    }
}

