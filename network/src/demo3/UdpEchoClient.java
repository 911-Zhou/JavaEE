package demo3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    private DatagramSocket socket = null;
    private String ServerIp;
    private int ServerPort;
    private final int SendLength = 4096;
    public UdpEchoClient(String ServerIp,int ServerPort) throws SocketException {
        socket = new DatagramSocket();
        this.ServerIp = ServerIp;
        this.ServerPort = ServerPort;
    }

    public void start() throws IOException {
        System.out.println("客户端启动");
        Scanner ScannerIn = new Scanner(System.in);
        while(true){
            System.out.print("->");
            String request = ScannerIn.nextLine();
//            //字符串边界
//            int requestLength = request.getBytes().length;
//
//            for(int i = 0;i<requestLength;i+=SendLength){
//                String part = request.substring(i,Math.min(requestLength,i+SendLength));
//                DatagramPacket RequestPacket = new DatagramPacket(part.getBytes(),part.getBytes().length, InetAddress.getByName(ServerIp),ServerPort);
//                socket.send(RequestPacket);
//            }
            DatagramPacket RequestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(ServerIp),ServerPort);
            socket.send(RequestPacket);

            DatagramPacket ResponsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(ResponsePacket);
            String response = new String(ResponsePacket.getData(),0, ResponsePacket.getLength());

            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1",6666);
        udpEchoClient.start();
    }

}
