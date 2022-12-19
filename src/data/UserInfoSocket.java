package data;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class UserInfoSocket {
    int port = 8888;
    String ip = "127.0.0.1";
    public Socket socket;

    private static UserInfoSocket instance = new UserInfoSocket();

    UserInfoSocket() {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            socket = new Socket(ipAddress,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserInfoSocket getInstance() {
        return instance;
    }
}
