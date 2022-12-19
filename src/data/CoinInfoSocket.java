package data;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class CoinInfoSocket {
    int port = 7777;
    String ip = "224.0.0.1";
    public MulticastSocket socket;

    private static CoinInfoSocket instance = new CoinInfoSocket();

    CoinInfoSocket() {
        try {
            socket = new MulticastSocket(port);
            InetAddress groupAddress = InetAddress.getByName(ip);
            socket.joinGroup(groupAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CoinInfoSocket getInstance() {
        return instance;
    }
}
