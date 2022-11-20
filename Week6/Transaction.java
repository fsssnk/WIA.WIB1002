package Week6;

import java.math.BigInteger;
import java.util.Scanner;


public class Transaction {
    public static void main(String[] args) {
        main();
    }
    private static void main(){
        try {
            // initialise balance
            BigInteger balance = new BigInteger("1000");
            MyQueue<String> data = new MyQueue<>();
            String input = "D 400 | W 300 | W 700 | D 450 | W 120";
            Scanner scr = new Scanner(System.in);
            System.out.print("Enter transactions: ");
            input = scr.nextLine();
            StringBuilder ledger = new StringBuilder();
            // regex for the string pattern
            String[] res = input.split("[| ]+");
            for (String str : res) data.enqueue(str);
            while (!data.isEmpty()) {
                char operation = data.dequeue().charAt(0);
                String value = data.dequeue();
                if (operation == 'D') balance.subtract(new BigInteger(value));
                else balance = balance.add(new BigInteger("-" + value));
                ledger.append(operation + " " + value + " --> ");
            }
            ledger.append(" Initial Balance: " + balance);
            System.out.println(ledger);
        }catch (Exception ex){
            System.out.println("Wrong format");
            ex.printStackTrace();
        }
    }
}
