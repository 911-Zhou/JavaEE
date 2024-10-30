package demo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udpEchoServer {
    private DatagramSocket socket  = null;

    public udpEchoServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        while(true) {
            //1.接收客户端请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());

            //2.计算处理客户端请求
            String response = echo(request);
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());

            //3.返回请求数据
            socket.send(responsePacket);

            //4.打印日志
            System.out.printf("[%s:%d], req = %s, res = %s \n",requestPacket.getAddress(),requestPacket.getPort(),request,response);
        }
    }

    private String echo(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        udpEchoServer udpEchoServer = new udpEchoServer(6666);
        udpEchoServer.start();
    }
}
