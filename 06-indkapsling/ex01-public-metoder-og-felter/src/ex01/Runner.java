package ex1;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(
                "12345678",
                0);

        var fields = BankAccount.class.getFields();
        var methods = BankAccount.class.getMethods();

        System.out.println("Fields: " + Arrays.toString(fields));
        System.out.println("Methods: " + Arrays.toString(methods));

        System.out.printf("Saldo: %10.2f kr.", account1.balance);
    }
}