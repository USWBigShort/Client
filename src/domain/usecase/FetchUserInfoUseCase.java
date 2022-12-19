package domain.usecase;

import domain.repository.CoinInfoRepository;
import domain.repository.UserInfoRepository;

import java.io.IOException;

public class FetchUserInfoUseCase {
    UserInfoRepository userInfoRepository;

    public FetchUserInfoUseCase(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

   public String execute() throws IOException {
        return userInfoRepository.receiveString();
   }
}
