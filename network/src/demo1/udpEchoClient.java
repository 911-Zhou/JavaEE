package demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class udpEchoClient {
    private DatagramSocket socket = null;
    private String ServerIP;
    private int ServerPort;
    private Scanner scanner = new Scanner(System.in);
    public udpEchoClient(String serverIP,int ServerPort) throws SocketException {
        socket = new DatagramSocket();
        this.ServerIP = serverIP;
        this.ServerPort = ServerPort;
    }

    public void start() throws IOException {
        while (true) {
            //1.读取用户输入
            System.out.print("->");
            String request = scanner.next();

            //2.构造udp请求，发送给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(this.ServerIP), this.ServerPort);
            socket.send(requestPacket);

            //3.从服务器读取到相应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            //4.打印响应
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        udpEchoClient udpEchoClient = new udpEchoClient("127.0.0.1",6060);
        udpEchoClient.start();
    }
}
