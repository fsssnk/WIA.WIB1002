package Week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class ProductCode {
    public static void main(String[] args) {
        main();
    }
    private static void main(){
        // store product details
        String[] key = new String[100];
        MyQueue<String>[] value = new MyQueue[100];
        int lastIndex = 0;
        // get resource
        URL path = ProductCode.class.getResource("lab6q3.txt");
        File file = new File(path.getFile());
        if (file.exists() && file.canRead()) {
            try {
                Scanner scr = new Scanner(file);
                while (scr.hasNext()) {
                    String code = scr.next();
                    String element = scr.next();
                    if (hasKey(key, code)) {
                        int indexOfKey = indexOfKey(key, code);
                        value[indexOfKey].enqueue(element);
                        // data.get(code).enqueue(element);
                    } else {
                        MyQueue<String> temp = new MyQueue<>();
                        temp.enqueue(element);
                        key[lastIndex] = code;
                        value[lastIndex] = temp;
                        lastIndex++;
                       // data.put(code, temp);
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                System.out.println("Problem with format in text file");
                ex.printStackTrace();
            }
            System.out.println("Product Code in Queue: ");
            Arrays.stream(key).filter(o -> o != null).forEach(k -> System.out.print(k + " --> "));
            System.out.println();
            for (int n=0; n<value.length; n++){
                if (value[n] == null) break;
                System.out.println("Product: " + key[n]);
                value[n].displayArrow();
                System.out.println();
            }
        }
    }
    public static boolean hasKey(String[] key, String value){
        for (int n=0; n<key.length; n++){
            if (key[n] == null){
                return false;
            }
            if (key[n].equals(value))
                return true;

        }
        return false;

    }
    public static int indexOfKey(String[] key, String value){
        for (int n=0; n<key.length; n++){
            if (key[n] != null && key[n].equals(value))
                return n;
        }
        return -1;
    }
}

