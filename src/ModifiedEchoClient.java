import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ModifiedEchoClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to server on port 12345");

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Enter a list of numbers");
            System.out.println("2. Enter an array of numbers");
            System.out.println("3. Enter a range of numbers");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the list of numbers separated by commas: ");
                    scanner.nextLine();
                    String input1 = scanner.nextLine();
                    String[] numbers = input1.split(" ");
                    int[] intNumbers = new int[numbers.length];

                    for (int i = 0; i < numbers.length; i++) {
                        intNumbers[i] = Integer.parseInt(numbers[i]);
                    }
                    String request1 = "list" + input1;
                    sendRequest(socket, request1);
                    break;
                case 2:
                    System.out.print("Enter the length of the array: ");
                    int length = scanner.nextInt();

                    int[] intArray = new int[length];
                    System.out.print("Enter the numbers separated by commas: ");

                    for (int i = 0; i < length; i++) {
                        intArray[i] = scanner.nextInt();
                    }
                    String request2 = "array" + arrayToString(intArray);
                    sendRequest(socket, request2);
                    break;
                case 3:
                    System.out.print("Enter the value of m: ");
                    int m = scanner.nextInt();
                    System.out.print("Enter the value of n: ");
                    int n = scanner.nextInt();
                    String request3 = "range " + m + "- " + n;
                    sendRequest(socket, request3);
                    break;
                case 4:
                    socket.close();
                    System.out.println("Connection closed");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void sendRequest(Socket socket, String request) throws Exception {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println(request);
        String response = in.readLine();
        System.out.println(response);
        out.close();
        in.close();
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public interface Result {
        String getResult();
    }
}