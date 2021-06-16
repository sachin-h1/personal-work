package interview.barbershop;

import java.util.concurrent.BlockingQueue;


public class Customer extends Thread {
    int id;
    BlockingQueue queue = null;

    public Customer(int i, BlockingQueue queue) {
        id = i;
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                this.queue.add(this.id);
                this.getHaircut();
            } catch (IllegalStateException e) {
                System.out.println("There are no free seats available. Customer " + this.id + " left the shop.");
            }
            break;
        }
    }

    public void getHaircut() {
        System.out.println("Customer " + this.id + " took a chair");
    }

}
