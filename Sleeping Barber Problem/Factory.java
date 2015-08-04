//Fred Tidwell
//Chima Uwazie
import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class Factory
{
    public static void main(String args[])
    {
        SecureRandom rand = new SecureRandom();
        CountDownLatch delay = new CountDownLatch(1);
        
        Buffer<Integer> server = new BoundedBuffer<Integer>();
        
        //Thread customerrThread = new Thread(new Customer(server));
        Thread barberThread = new Thread(new Barber(server));
        
        //producerThread.start();
        barberThread.start();
        
       
        
    }
}
