package Week6;

public class Investor {
    public static void main(String[] args) {
        // store details in 2 queue
        MyQueue<Integer> share = new MyQueue<>();
        share.enqueue(100);
        share.enqueue(20);
        share.enqueue(200);
        share.enqueue(150);
        MyQueue<Integer> shareCopy = share.copy();

        MyQueue<Integer> price  = new MyQueue<>();
        price.enqueue(20);
        price.enqueue(24);
        price.enqueue(36);
        price.enqueue(30);
        MyQueue<Integer> priceCopy = price.copy();

        MyQueue<String> log  = new MyQueue<>();
        int day = 1;
        var currentHighest = Integer.MIN_VALUE;

        while (!(share.isEmpty() && price.isEmpty())){
            if (price.peek() > currentHighest){
                currentHighest = price.dequeue();
                log.enqueue("Day: "+day +" Bought: " + share.dequeue() +" shares at $" + currentHighest);
                ++day;
            }else {
                log.enqueue("Day: "+day +" Sold: " + share.dequeue() +" shares at $" + currentHighest);
                int profit = 0;
                int currentPrice = price.dequeue();
                for (int n = 0; n < day-1; n++){
                    int sh = shareCopy.dequeue();
                    int pc = priceCopy.dequeue();
                    profit += (sh * (currentPrice - pc));
                }
                log.enqueue("Total profit: "+ profit);
            }
        }
        log.displayArrow();
    }
}
