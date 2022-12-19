package presentation;

import data.UserInfoSocket;
import data.repositoryimpl.CoinInfoRepositoryImpl;
import data.CoinInfoSocket;
import data.repositoryimpl.UserInfoRepositoryImpl;
import domain.repository.CoinInfoRepository;
import domain.repository.UserInfoRepository;
import domain.usecase.FetchCoinInfoUseCase;
import domain.usecase.FetchUserInfoUseCase;

import java.io.IOException;
import java.net.*;

public class Presenter {
    public static void main(String[] args) throws IOException {

        View view = new View("USW BigShort");
        view.setVisible(true);

        CoinInfoSocket coinInfoSocket = CoinInfoSocket.getInstance();
        CoinInfoRepository coinInfoRepository = new CoinInfoRepositoryImpl(coinInfoSocket);
        FetchCoinInfoUseCase fetchCoinInfoUseCase = new FetchCoinInfoUseCase(coinInfoRepository);

        UserInfoSocket userInfoSocket = UserInfoSocket.getInstance();
        UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl(userInfoSocket);
        FetchUserInfoUseCase fetchUserInfoUseCase = new FetchUserInfoUseCase(userInfoRepository);

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
                    System.out.println(fetchUserInfoUseCase.execute());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveTCPSocket.start();
    }
}

