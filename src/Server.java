import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client connection...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input and output streams for the client socket
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);


            // Prepare the request to send to the server
            String request = "performCalculation";

            // Send the request to the server
            outputWriter.println(request);
            System.out.println("Sent request to server: " + request);

            // Receive and display the result from the server
            String result = inputReader.readLine();
            System.out.println("Received result from server: " + result);
            // Close the connection
            clientSocket.close();
            serverSocket.close();
            System.out.println("Connection closed. Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


