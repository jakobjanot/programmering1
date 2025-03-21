package dk.kea.prog1.ex01;

public class SavingsAccount {
    double balance;
    double rate;
    String owner;

    SavingsAccount(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
        this.rate = 0.05;
    }

   void applyInterest() {
        this.balance = this.balance * (1 + rate);
    }
}

/*
class SavingsAccount {
    private double balance;
    private double rate = 0.05;
    private String owner;

    SavingsAccount(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
    }

    public void applyInterest() {
        double interest = balance * (1 + rate);
        deposit(interest);
    }

    public void withdraw(double amount) {
        if (amount > balance)
            return;

        setBalance(balance - amount);
    }

    public void deposit(double amount) {
        if (amount < 0)
            return;

        setBalance(balance + amount);
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        if (balance < 0)
            return;

        this.balance = balance;
    }
}

 */