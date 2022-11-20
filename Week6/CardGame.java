package Week6;

import java.util.Arrays;
import java.util.HashMap;

public class CardGame {
    public static void main(String[] args) {
        main();
    }
    private static void main(){
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        HashMap<String, Integer> configuration= new HashMap<>();
        MyQueue<String> p1Log = new MyQueue<>();
        p1Log.enqueue("Player 1 Card\n");
        MyQueue<String> p2Log = new MyQueue<>();
        p2Log.enqueue("Player 2 Card\n");
        var p1Score = 0;
        var p2Score = 0;
        for (int n = 0; n<5; n++){
            int[] p1Result = getRandom();
            int[] p2Result = getRandom();
            if(p1Result[0] < p2Result[0]){
                ++p1Score;
            }else if (p1Result[0] > p2Result[0]){
                ++p2Score;
            }else {
                if (p1Result[1] > p2Result[1]) ++p1Score;
                else if (p1Result[1] < p2Result[1]) ++p2Score;
                else {
                    // draw
                }
            }
            p1Log.enqueue(p1Result[1] +" " + colors[p1Result[0]]);
            p2Log.enqueue(p2Result[1] + " " + colors[p2Result[0]]);
        }
        p1Log.displayArrow();
        System.out.println();
        p2Log.displayArrow();
        System.out.println();
        System.out.println("Player 1 Score: " + p1Score);
        System.out.println("Player 2 Score: " + p2Score);
        if(p1Score > p2Score) System.out.println("Player 1 wins!");
        else if (p1Score < p2Score) System.out.println("Player 2 wins!");
        else System.out.println("Draw");
    }
    // return a random draw
    private static int[] getRandom(){
        int randomColor = (int) Math.round(3 * Math.random());
        int randomScore = (int) Math.round(9 * Math.random());
        return new int[]{randomColor, randomScore};
    }
}