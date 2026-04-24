import java.util.ArrayList;

public class Account {
    private double balance;
    private String accountHolder;
    private ArrayList<Double> transactionHistory;

    public Account(String accountHolder, double initialBalance) {
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountHolder = accountHolder.trim();
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void processTransaction(double amount) throws InSufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        }
        if (amount > balance) {
            throw new InSufficientFundsException(
                String.format(
                    "Insufficient funds: requested %.2f, available %.2f.",
                    amount,
                    balance
                )
            );
        }

        balance -= amount;
        logTransaction(-amount);
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative.");
        }

        balance += amount;
        logTransaction(amount);
    }

    private void logTransaction(double amount) {
        transactionHistory.add(amount);
        if (transactionHistory.size() > 5) {
            transactionHistory.remove(0);
        }
    }

    public void printMiniStatement() {
        System.out.println("\n===== FinSafe Mini Statement =====");
        System.out.println("Account Holder: " + accountHolder);
        System.out.printf("Current Balance: %.2f%n", balance);

        if (transactionHistory.isEmpty()) {
            System.out.println("No recent transactions.");
            return;
        }

        System.out.println("Recent Transactions (latest 5):");
        for (int index = 0; index < transactionHistory.size(); index++) {
            double amount = transactionHistory.get(index);
            String type = amount >= 0 ? "Deposit " : "Withdraw";
            System.out.printf(
                "%d. %s %.2f%n",
                index + 1,
                type,
                Math.abs(amount)
            );
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
