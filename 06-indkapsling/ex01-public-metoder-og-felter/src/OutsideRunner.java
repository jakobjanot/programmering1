import dk.kea.prog1.ex01.SavingsAccount;

public class OutsideRunner {
    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount(1000, "Kim");
        account.applyInterest();

        System.out.println("Saldo for " + account.owner + ": " + account.balance);

    }
}
