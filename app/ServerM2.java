package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Djok on 5/29/2017.
 * With Soul
 */
public class ServerM2 extends Thread{
    private static final int PORT = 6969;
    private ServerSocket serverSocket;

    public ServerM2() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            new ServeClient(socket).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
