import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final String SERVER_IP = "localhost";
        final int PORT = 12345;

        Scanner scanner = new Scanner(System.in);
        Divisors divisors = new Divisors();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Enter a list of numbers");
            System.out.println("2. Enter an array of numbers");
            System.out.println("3. Enter a range of numbers");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the list of numbers separated by spaces: ");
                    scanner.nextLine(); // Consume the new line character left over from nextInt()
                    String input1 = scanner.nextLine();
                    String[] numbers = input1.split(" ");
                    int[] intNumbers = new int[numbers.length];
                    for (int i = 0; i < numbers.length; i++) {
                        intNumbers[i] = Integer.parseInt(numbers[i]);
                    }
                    divisors = new Divisors(intNumbers);
                }
                case 2 -> {
                    System.out.print("Enter the length of the array: ");
                    int length = scanner.nextInt();
                    int[] intArray = new int[length];
                    System.out.print("Enter the numbers separated by spaces: ");
                    for (int i = 0; i < length; i++) {
                        intArray[i] = scanner.nextInt();
                    }
                    divisors = new Divisors(intArray);
                }
                case 3 -> {
                    System.out.print("Enter the value of m: ");
                    int m = scanner.nextInt();
                    System.out.print("Enter the value of n: ");
                    int n = scanner.nextInt();
                    divisors = new Divisors(m, n);
                }
                case 4 -> System.exit(0);
                default -> {
                    System.out.println("Invalid choice, please try again.");
                    continue;
                }
            }

            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("Connected to server: " + SERVER_IP + ":" + PORT);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(divisors);
            System.out.println("Sent Divisors object to server.");

            String response = (String) in.readObject();
            System.out.println("Response from server: " + response);

            socket.close();
        }
    }
}