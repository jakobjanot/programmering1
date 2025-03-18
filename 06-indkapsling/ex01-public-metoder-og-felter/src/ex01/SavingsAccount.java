package ex01;

class SavingsAccount {
    double balance;
    double rate;
    String owner;

    SavingsAccount(double balance, String owner) {
        this.balance = balance;
        this.owner = owner;
        this.rate = 0.05;
    }

   void addInterest() {
        balance = balance * (1 + rate);
    }
}