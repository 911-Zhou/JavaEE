package demo6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket = null;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true) {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    Connection(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }

    private void Connection(Socket clientSocket) throws IOException {
        System.out.printf("客户端[%s:%d]上线!\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try(InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);

            while(true){
                //连接断开就终止
                //这里涉及到 Socket 的流阻塞特性。
                //流的阻塞特性：Socket 的输入流是阻塞的，如果没有数据到达，scanner.hasNext() 就会阻塞，等待数据到来。当连接关闭时，流会返回 false，表示没有更多数据。
                //TCP 协议特性：TCP 连接是面向流的，不会有显式的“没有数据”标志。除非发送数据，或连接断开，否则会保持等待状态。
                //EOF (End of File) 信号：Scanner 的 hasNext() 只有在检测到流的末尾（EOF）时才返回 false，即连接断开才会返回 false。而如果连接未关闭、但没有数据传输，hasNext() 仍会阻塞等待。
                if(!scanner.hasNext()){
                    break;
                }

                String request = scanner.next();

                String response = Handle(request);
                printWriter.println(response);
                printWriter.flush();

                System.out.printf("客户端[%s:%d],req = %s, res = %s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.printf("客户端[%s:%d]下线!\n",clientSocket.getInetAddress(),clientSocket.getPort());
            clientSocket.close();
        }
    }

    private String Handle(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(6666);
        server.start();
    }
}
