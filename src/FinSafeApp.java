import java.util.Scanner;

public class FinSafeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = initializeAccount(scanner);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    handleDeposit(scanner, account);
                    break;
                case 2:
                    handleWithdrawal(scanner, account);
                    break;
                case 3:
                    account.printMiniStatement();
                    break;
                case 4:
                    System.out.println("Exiting FinSafe. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu option. Please select 1-4.");
                    break;
            }
        }

        scanner.close();
    }

    private static Account initializeAccount(Scanner scanner) {
        System.out.println("===== Welcome to FinSafe =====");

        String accountHolder;
        do {
            System.out.print("Enter account holder name: ");
            accountHolder = scanner.nextLine().trim();
            if (accountHolder.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }
        } while (accountHolder.isEmpty());

        while (true) {
            double initialBalance = readDouble(scanner, "Enter initial balance: ");
            try {
                Account account = new Account(accountHolder, initialBalance);
                System.out.printf(
                    "Account created for %s with balance %.2f%n",
                    account.getAccountHolder(),
                    account.getBalance()
                );
                return account;
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== FinSafe Menu =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View History");
        System.out.println("4. Exit");
    }

    private static void handleDeposit(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter deposit amount: ");
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static void handleWithdrawal(Scanner scanner, Account account) {
        double amount = readDouble(scanner, "Enter withdrawal amount: ");
        try {
            account.processTransaction(amount);
            System.out.printf("Withdrawal successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException | InSufficientFundsException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid amount. Please enter a numeric value.");
            }
        }
    }
}
