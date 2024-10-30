package demo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoServer {
    private ServerSocket serverSocket = null;

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true){
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(()->{
                try {
                    processConnection(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }

    //针对一个连接提供逻辑处理
    private void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线.\n",clientSocket.getInetAddress(),clientSocket.getPort());

        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()){
            while(true) {
                Scanner scanner = new Scanner(inputStream);
                PrintWriter printWriter = new PrintWriter(outputStream);
                //客户端断开连接，终止服务
                if(!scanner.hasNext()){
                    break;
                }
                //获取请求
                String request = scanner.nextLine();
                //处理请求返回响应
                String response = Echo(request);
                //写回响应
                printWriter.println(response);
                //数据都在缓存区，没有立刻发送
                printWriter.flush();
                System.out.printf("[%s:%d],req = %s, res = %s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.printf("[%s:%d]，客户端下线.",clientSocket.getInetAddress(),clientSocket.getPort());
            clientSocket.close();
        }
    }

    private String Echo(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer tcpEchoServer = new TcpEchoServer(6666);
        tcpEchoServer.start();
    }

}
