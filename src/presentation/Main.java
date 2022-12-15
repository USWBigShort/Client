package presentation;

import data.CoinInfoRepositoryImpl;
import data.CoinInfoSocket;
import domain.CoinInfoRepository;
import domain.usecase.FetchCoinInfoUseCase;

import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        CoinInfoSocket coinInfoSocket = CoinInfoSocket.getInstance();
        CoinInfoRepository coinInfoRepository = new CoinInfoRepositoryImpl(coinInfoSocket);
        FetchCoinInfoUseCase fetchCoinInfoUseCase = new FetchCoinInfoUseCase(coinInfoRepository);

        Thread receiveBroadCastSocket = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println(fetchCoinInfoUseCase.execute());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        receiveBroadCastSocket.start();

        Thread receiveTCPSocket = new Thread(
                () -> {
                    /**
                     * TODO 서버로부터 매수/매도 요청 받아오기
                     * 받아온 정보 gui에 갱신
                     */
                    try {
                        Socket clientSocket = new Socket(Inet4Address.getByName("127.0.0.1"), 4000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        receiveTCPSocket.start();
    }
}

