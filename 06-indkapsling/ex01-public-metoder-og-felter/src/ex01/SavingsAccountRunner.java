package ex01;

public class SavingsAccountRunner {
    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount(0, "Kim");

        System.out.println("BALANCE for " + account.owner + ": " + account.balance);
    }
}
