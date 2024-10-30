package demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket Serversocket = null;

    public TcpEchoClient(String Ip,int port) throws IOException {
        Serversocket = new Socket(InetAddress.getByName(Ip),port);
    }

    public void start() throws IOException {
        System.out.println("客户端启动");
        Scanner scannerIn = new Scanner(System.in);
        try(OutputStream outputStream = Serversocket.getOutputStream();
            InputStream inputStream = Serversocket.getInputStream()){
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);

            while(true) {
                System.out.print("->");
                String request = scannerIn.nextLine();
                printWriter.println(request);
                //数据都在缓存区，没有立刻发送
                printWriter.flush();
                if(!scanner.hasNext()){
                    System.out.println("无法连接至服务器");
                    break;
                }
                String response = scanner.nextLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            Serversocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient("127.0.0.1",6666);
        tcpEchoClient.start();
    }
}
