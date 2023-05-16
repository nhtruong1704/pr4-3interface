import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final int PORT = 12345;
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            try {
                System.out.println("Waiting for client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                Divisors divisors = (Divisors) in.readObject();
                System.out.println("Received parameter data from client: " + divisors.toString());

                String result = divisors.getResult();
                out.writeObject(result);
                System.out.println("Sent result to client: " + result);

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            }
        }
    }
}
