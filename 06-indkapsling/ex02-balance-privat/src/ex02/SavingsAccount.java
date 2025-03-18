package ex02;

class SavingsAccount {
    private double balance;
    private double rate;
    private String owner;

    SavingsAccount(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
        this.rate = 0.05;
    }

    public void addInterest() {
        balance = balance * (1 + rate);
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }
}