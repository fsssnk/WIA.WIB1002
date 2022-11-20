package Week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class ProductCode {
    public static void main(String[] args) {
        main();
    }
    private static void main(){
        // store product details
        HashMap<String, MyQueue<String>> data = new HashMap<>();
        // get resource
        URL path = ProductCode.class.getResource("lab6q3.txt");
        File file = new File(path.getFile());
        if (file.exists() && file.canRead()) {
            try {
                Scanner scr = new Scanner(file);
                while (scr.hasNext()) {
                    String code = scr.next();
                    String element = scr.next();
                    if (data.containsKey(code)) {
                        data.get(code).enqueue(element);
                    } else {
                        MyQueue<String> temp = new MyQueue<>();
                        temp.enqueue(element);
                        data.put(code, temp);
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("Problem with format in text file");
                ex.printStackTrace();
            }

            System.out.println("Product Code in Queue: ");
            data.forEach((k, v) -> System.out.print(k + " --> "));
            System.out.println();
            data.forEach((k,v) -> {
                System.out.println("Product: " + k);
                v.displayArrow();
                System.out.println();
            });
        }
    }
}

