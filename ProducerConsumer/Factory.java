//Fred Tidwell
//Chima Uwazie
import java.util.Date;
public class Factory
{
    public static void main(String args[])
    {
        Buffer<Date> server = new BoundedBuffer<Date>();
        
        Thread producerThread = new Thread(new Producer(server));
        Thread consumerThread = new Thread(new Consumer(server));
        
        producerThread.start();
        consumerThread.start();
    }
}
