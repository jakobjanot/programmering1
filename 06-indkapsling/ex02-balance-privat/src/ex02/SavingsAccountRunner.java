package ex02;

public class SavingsAccountRunner {
    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount(200, "Kim");
        account.withdraw(1000.0);

        System.out.println("BALANCE for " + account.getOwner() + ": " + account.getBalance());
    }
}
