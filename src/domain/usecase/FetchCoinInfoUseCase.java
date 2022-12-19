package domain.usecase;

import domain.repository.CoinInfoRepository;

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
