package data.repositoryimpl;

import data.CoinInfoSocket;
import data.UserInfoSocket;
import domain.repository.CoinInfoRepository;
import domain.repository.UserInfoRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;

public class UserInfoRepositoryImpl implements UserInfoRepository {
    UserInfoSocket userInfoSocket;
    byte[] buffer = new byte[1024 * 1024];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    BufferedReader br;

    public UserInfoRepositoryImpl(UserInfoSocket userInfoSocket) throws IOException {

        this.userInfoSocket = userInfoSocket;
        PrintStream out = new PrintStream(this.userInfoSocket.socket.getOutputStream());
        InputStreamReader isr = new InputStreamReader(this.userInfoSocket.socket.getInputStream());
        br = new BufferedReader(isr);
    }

    @Override
    public String receiveString() throws IOException {
        return br.readLine();
    }
}
