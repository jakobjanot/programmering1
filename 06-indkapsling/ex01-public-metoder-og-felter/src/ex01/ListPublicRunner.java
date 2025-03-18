package ex01;

import java.util.Arrays;

public class ListPublicRunner {
    public static void main(String[] args) {
        System.out.println("Fields: " + Arrays.toString(SavingsAccount.class.getFields()));
        System.out.println("Methods: " + Arrays.toString(SavingsAccount.class.getMethods()));
    }
}