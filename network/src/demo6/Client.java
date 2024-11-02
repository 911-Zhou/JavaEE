package demo6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket serverSocket = null;

    public Client(String host, int port) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(host);
        serverSocket = new Socket(inetAddress, port);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("客户端启动！");
        try(InputStream inputStream = serverSocket.getInputStream();
            OutputStream outputStream = serverSocket.getOutputStream()){
            PrintWriter printWriter = new PrintWriter(outputStream);
            Scanner scannerIn = new Scanner(inputStream);

            while (true) {
                System.out.print("请输入请求->");
                String request = scanner.next();

                printWriter.println(request);
                printWriter.flush();

                if (!scannerIn.hasNext()) {
                    System.out.println("服务器异常");
                    break;
                }

                String response = scannerIn.nextLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost",6666);
        client.start();
    }
}
