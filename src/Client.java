import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 12345);

            // Create input and output streams for the socket
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

            String request = inputReader.readLine();
            if (request != null) {
                System.out.println("Received request from client: " + request);
                // Perform the calculation of Divisors and get the result
                String result = performCalculation(request);

                // Send the result back to the client
                outputWriter.println(result);
                System.out.println("Sent result to client: " + result);
            }

            // Close the connection
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String performCalculation(String request) {
        // Create an instance of Divisors and get the result
        Divisors divisors = new Divisors();
        return divisors.getResult();
    }
}
