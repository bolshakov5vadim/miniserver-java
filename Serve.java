import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;


public class Serve
{

    public static void main(String[] args) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true)
        {
            Socket sock = serverSocket.accept();// сокет, через который сервер общается с клиентом,
            // кроме него - клиент и сервер никак не связаны
            Thread t = new Thread(new ClientHandler(sock));

            t.start();//новые клиенты
        }
    }
}


class ClientHandler implements Runnable
{
    Socket socket;
    public ClientHandler(Socket socket)
    {
        this.socket = socket;

    }

    @Override//переопределение базового метода
            public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());// поток чтения из сокета
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner send=new Scanner(System.in);
            System.out.println("ServerStarted");
            while (true) {
                System.out.println("Data from client: ");
                System.out.println(in.nextLine());
                System.out.println("Write to client: ");
                //System.out.println("Reading message");
                String s = send.nextLine();
                out.println(s);
                out.flush();

            }
        } catch (IOException e) {
            System.err.println("IO Exception");
        }

    }
}