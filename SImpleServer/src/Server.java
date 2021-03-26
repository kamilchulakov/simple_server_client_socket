import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static BufferedWriter bufferedWriter;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9000);) {
            System.out.println("Server started.");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected.");
                    bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())
                    );
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream())
                    );
                    while (true) {
                        String request = bufferedReader.readLine();
                        if (!request.isEmpty()) if (request.equals("exit")) System.exit(0);
                        System.out.printf("You typed: %s.%n", request);
                        bufferedWriter.write(String.format("You typed: %s.", request));
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
