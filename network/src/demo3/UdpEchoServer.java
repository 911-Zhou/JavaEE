package demo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//111111

public class UdpEchoServer {
    private DatagramSocket socket = null;

    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true){
            DatagramPacket RequestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(RequestPacket);
            String request = new String(RequestPacket.getData(),0,RequestPacket.getLength());

            String response = echo(request);
            DatagramPacket ResponsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,RequestPacket.getAddress(),RequestPacket.getPort());

            socket.send(ResponsePacket);

            System.out.printf("[%s:%d], req = %s, res = %s\n",RequestPacket.getAddress(),RequestPacket.getPort(),request,response);
        }
    }

    private String echo(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer = new UdpEchoServer(6666);
        udpEchoServer.start();
    }
}
