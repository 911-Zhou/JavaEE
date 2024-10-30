package demo2;

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

    public udpEchoClient(String IP,int Port) throws SocketException {
        this.ServerIP = IP;
        this.ServerPort = Port;
        socket = new DatagramSocket();
    }

    public void start() throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            //1.读取控制台，获取用户发送的请求
            System.out.print("->");
            String request = scanner.next();
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(), request.getBytes().length,
                    InetAddress.getByName(this.ServerIP), this.ServerPort);

            //2.发送请求
            socket.send(requestPacket);

            //3.接收服务器返回结果
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

            //4.打印日志
            System.out.println("服务器返回：" + response);
        }
    }

    public static void main(String[] args) throws IOException {
        udpEchoClient udpEchoClient = new udpEchoClient("47.108.28.88",9090);
        udpEchoClient.start();
    }
}
