package domain.usecase;

import domain.repository.UserInfoRepository;

import java.io.IOException;

public class PostUserInfoUseCase {
    UserInfoRepository userInfoRepository;

    public PostUserInfoUseCase(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

   public void execute(String msg) throws IOException {
        userInfoRepository.postString(msg);
   }
}
