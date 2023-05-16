import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ModifiedEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started on port 12345");

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request = in.readLine();
                System.out.println("Received request: " + request);

                Divisors divisors = null;
                String[] requestParams = request.split("\\|");
                switch (requestParams[0]) {
                    case "list", "array" -> {
                        String[] numbers = requestParams[1].split(",");
                        int[] intNumbers = new int[numbers.length];
                        for (int i = 0; i < numbers.length; i++) {
                            intNumbers[i] = Integer.parseInt(numbers[i].trim());
                        }
                        divisors = new Divisors(intNumbers);
                    }
                    case "range" -> {
                        String[] rangeParams = requestParams[1].split(",");
                        int m = Integer.parseInt(rangeParams[0].trim());
                        int n = Integer.parseInt(rangeParams[1].trim());
                        divisors = new Divisors(m, n);
                    }
                    default -> System.out.println("Invalid request");
                }

                String response = divisors != null ? divisors.getResult() : "Invalid request";
                out.println(response);

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Connection closed");
            } catch (IOException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            }
        }

    }
}
