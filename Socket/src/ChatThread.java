import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ChatThread extends Thread{
    Socket socket;

    public ChatThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
            String line = "";
            while (true) {
                line = br.readLine(); // Receive data from client
                System.out.println("From " + socket.getInetAddress().getHostAddress() +">"+line);
                if (line == null || line.equalsIgnoreCase("quit")) break;
                out.println("Response from K61 server:>>" + line);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("Connection lost from: " + socket.getInetAddress().getHostAddress());
        }
    }
}
