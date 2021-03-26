import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 9000);
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            )
        {
            System.out.println("Connected to server.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String request = scanner.nextLine();
                bufferedWriter.write(request);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (request.equals("exit")) System.exit(0);
                String result = bufferedReader.readLine();
                System.out.printf("Result: %s\n", result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
