package domain.usecase;

import data.CoinInfoSocket;
import domain.CoinInfoRepository;

import java.io.IOException;

public class FetchCoinInfoUseCase {
    CoinInfoRepository coinInfoRepository;

    public FetchCoinInfoUseCase(CoinInfoRepository coinInfoRepository) {
        this.coinInfoRepository = coinInfoRepository;
    }

   public String execute() throws IOException {
        return coinInfoRepository.receiveString();
   }
}
