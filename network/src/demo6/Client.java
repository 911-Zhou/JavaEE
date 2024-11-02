package demo6;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket clientSocket = null;

    public Client(String host, int port) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(host);
        clientSocket = new Socket(inetAddress, port);
    }

    public void start() {
        System.out.println("客户端启动！");
        while (true) {

        }
    }
}
