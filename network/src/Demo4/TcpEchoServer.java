package Demo4;

import com.sun.source.tree.Scope;

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
        Socket clientSocket =  serverSocket.accept();
        Thread thread = new Thread(()->{
            try {
                Connection(clientSocket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    private void Connection(Socket clientSocket) throws IOException {
        System.out.printf("客户端[%s:%d]上线！\n",clientSocket.getInetAddress(),clientSocket.getPort());
        try(InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream())
        {
            PrintWriter printWriter = new PrintWriter(outputStream);
            Scanner scannerIn = new Scanner(inputStream);

            while(true){
                if(!scannerIn.hasNext()){
                    break;
                }

                String request = scannerIn.nextLine();
                String respones = Echo(request);

                printWriter.println(respones);
                printWriter.flush();

                System.out.printf("客户端[%s:%d],req = %s,res = %s\n",clientSocket.getInetAddress(),clientSocket.getPort(),request,respones);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.printf("客户端[%S:%d]下线\n",clientSocket.getInetAddress(),clientSocket.getPort());
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
