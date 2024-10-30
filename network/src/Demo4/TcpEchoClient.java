package Demo4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpEchoClient {
    private Socket serverSocket = null;
    public TcpEchoClient(String Ip,int port) throws IOException {
//        try {
//            InetAddress ServerIp = InetAddress.getByName(Ip);
//        } catch (UnknownHostException e) {
//            throw new RuntimeException(e);
//        }
        serverSocket = new Socket(Ip,port);
    }

    public void start(){
        Scanner scannerIn = new Scanner(System.in);
        try(InputStream inputStream = serverSocket.getInputStream();
            OutputStream outputStream = serverSocket.getOutputStream()) {
            PrintWriter printWriter = new PrintWriter(outputStream);
            Scanner scannerStream = new Scanner(inputStream);

            while(true) {
                System.out.print("->");
                String request = scannerIn.nextLine();
                printWriter.println(request);
                printWriter.flush();

                if (!scannerStream.hasNext()) {
                    System.out.println("服务器异常");
                    break;
                }
                String response = scannerStream.nextLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient = new TcpEchoClient("127.0.0.1",6666);
        tcpEchoClient.start();
    }
}
