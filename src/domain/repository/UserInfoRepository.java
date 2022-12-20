package domain.repository;

import java.io.IOException;

public interface UserInfoRepository {
    String receiveString() throws IOException;

    void postString(String msg) throws IOException;
}
