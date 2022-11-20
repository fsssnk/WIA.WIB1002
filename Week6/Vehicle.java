package Week6;

public class Vehicle{
    private String type;
    private int priority;
    private static int total;
    private int id;
    public Vehicle(String type, int priority){
        this.type = type;
        this.priority = priority;
        this.id = total++;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", priority=" + priority +
                ", id=" + id +
                '}';
    }

    static class VehicleQueue{
        int maxPriority;
        MyQueue<Vehicle>[] data;
        VehicleQueue(int maxPriority){
            this.maxPriority = maxPriority;
            this.data = new MyQueue[maxPriority+1];
        }
        void enqueue(Vehicle v){
            if(data[maxPriority - v.getPriority()] != null){
                data[maxPriority - v.getPriority()].enqueue(v);
            }else {
                MyQueue<Vehicle> temp = new MyQueue<>();
                temp.enqueue(v);
                data[maxPriority - v.getPriority()] = temp;
            }
        }
       Vehicle dequeue(){
            for (int n=0; n<maxPriority+1; n++){
                if(data[n] != null && !data[n].isEmpty()){
                    return data[n].dequeue();
                }
            }
            return null;
        }
    }
    public static void main(String[] args) {
        Vehicle v = new Vehicle("Car", 1);
        Vehicle v1 = new Vehicle("Motor", 0);
        Vehicle v2 = new Vehicle("Truck", 2);
        VehicleQueue vq = new VehicleQueue(2);
        vq.enqueue(v);  vq.enqueue(v1); vq.enqueue(v2);

        System.out.println(vq.dequeue());
        System.out.println(vq.dequeue());
        System.out.println(vq.dequeue());
    }
}
