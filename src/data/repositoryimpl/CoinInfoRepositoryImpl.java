package data.repositoryimpl;

import data.CoinInfoSocket;
import domain.repository.CoinInfoRepository;

import java.io.IOException;
import java.net.DatagramPacket;

public class CoinInfoRepositoryImpl implements CoinInfoRepository {
    CoinInfoSocket coinInfoSocket;
    byte[] buffer = new byte[1024 * 1024];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    public CoinInfoRepositoryImpl(CoinInfoSocket coinInfoSocket) {
        this.coinInfoSocket = coinInfoSocket;
    }

    @Override
    public String receiveString() throws IOException {
        coinInfoSocket.socket.receive(packet);
        byte[] receiveData = packet.getData();
        String data = new String(receiveData, 0, packet.getLength());
        return data;
    }
}
