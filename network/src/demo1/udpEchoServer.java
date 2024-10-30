package demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static java.lang.Thread.sleep;

public class udpEchoServer{
    private DatagramSocket socket = null;

    public udpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        while(true){
            //读取客户端的请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0, requestPacket.getLength());

            //处理请求
            String response = process(request);

            //返回响应请求
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

            //打印日志
            System.out.printf("[%s:%d],req = %s,res = %s \n",requestPacket.getSocketAddress(),responsePacket.getPort(),request,response);
        }
    }

    private String process(String request) {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  request;
    }


    public static void main(String[] args) throws IOException {
        udpEchoServer udpEchoServer = new udpEchoServer(6060);
        udpEchoServer.start();
    }
}
