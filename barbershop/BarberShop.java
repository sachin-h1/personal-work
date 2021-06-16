package interview.barbershop;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BarberShop extends Thread {

    //Assuming 15 number of chairs available in waiting room
    public static final int NO_OF_CHAIRS = 5;
    private static final long CUSTOMER_ARRIVAL_TIME_INTERVAL = 2000;
    public static BlockingQueue queue = new ArrayBlockingQueue(NO_OF_CHAIRS);

    public void run() {

        //Barber is initialized
        //shares the shop queue
        Barber barber = new Barber(BarberShop.queue);
        barber.start();

        //Assuming is 20 customer arrive in a given interval of time back for simulation purpose
        //shares the shop queue
        for (int i = 1; i < 20; i++) {
            Customer customer = new Customer(i, BarberShop.queue);
            customer.start();
            try {
                sleep(CUSTOMER_ARRIVAL_TIME_INTERVAL);
            } catch (InterruptedException ex) {
                System.out.println("Unexpected error in customer arrival");
            };
        }
    }

    public static void main(String args[]) {
        BarberShop barberShop = new BarberShop();
        barberShop.start();
    }

}
