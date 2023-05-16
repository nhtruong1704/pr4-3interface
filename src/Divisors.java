import java.util.Scanner;

public class Divisors implements Result {
    private int[] numbers;
    private int m;
    private int n;

    public Divisors(int[] numbers) {
        this.numbers = numbers;
    }

    public Divisors(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public Divisors() {
    }

    @Override
    public String getResult() {
        StringBuilder result = new StringBuilder();

        if (numbers != null) {
            for (int num : numbers) {
                result.append("Divisors of ").append(num).append(": ");
                for (int i = 1; i <= num; i++) {
                    if (num % i == 0) {
                        result.append(i).append(" ");
                    }
                }
                result.append("\n");
            }
        } else {
            for (int i = m; i <= n; i++) {
                result.append("Divisors of ").append(i).append(": ");
                for (int j = 1; j <= i; j++) {
                    if (i % j == 0) {
                        result.append(j).append(" ");
                    }
                }
                result.append("\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
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
                case 1:
                    System.out.print("Enter the list of numbers separated by spaces: ");
                    scanner.nextLine(); // Consume the new line character left over from nextInt()
                    String input1 = scanner.nextLine();
                    String[] numbers = input1.split(" ");
                    int[] intNumbers = new int[numbers.length];

                    for (int i = 0; i < numbers.length; i++) {
                        intNumbers[i] = Integer.parseInt(numbers[i]);
                    }

                    divisors = new Divisors(intNumbers);
                    System.out.println(divisors.getResult());
                    break;

                case 2:
                    System.out.print("Enter the length of the array: ");
                    int length = scanner.nextInt();

                    int[] intArray = new int[length];
                    System.out.print("Enter the numbers separated by spaces: ");
                    for (int i = 0; i < length; i++) {
                        intArray[i] = scanner.nextInt();
                    }

                    divisors = new Divisors(intArray);
                    System.out.println(divisors.getResult());
                    break;

                case 3:
                    System.out.print("Enter the value of m: ");
                    int m = scanner.nextInt();
                    System.out.print("Enter the value of n: ");
                    int n = scanner.nextInt();

                    divisors = new Divisors(m, n);
                    System.out.println(divisors.getResult());
                    break;

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
    public interface Result {
        String getResult();
    }
}
