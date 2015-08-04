//Fred Tidwell
//Chima Uwazie
import java.util.Date;
import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class Consumer implements Runnable
{
    private Buffer<Date> b;
    SecureRandom rand = new SecureRandom();
    CountDownLatch delay = new CountDownLatch(1);
    public Consumer(Buffer<Date> server)
    {
        b = server;
    }
    public void run()
    {
        while(true)
        {
            int x = rand.nextInt(5001);
            //int x = rand.nextInt(6);
            System.out.println("Consumer napping");
            
            try
            {
                delay.await(x, TimeUnit.MILLISECONDS);
                //delay.await(x, TimeUnit.SECONDS);
            }
            catch(Exception e){}
            Date d = new Date();
            System.out.println("Consumer wants to consume");
        
            b.remove();
        }
    }
}
