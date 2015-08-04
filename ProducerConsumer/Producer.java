//Fred Tidwell
//Chima Uwazie
import java.util.Date;
import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class Producer implements Runnable
{
    private Buffer<Date> b;
    public SecureRandom rand = new SecureRandom();
    public CountDownLatch delay = new CountDownLatch(1);
    public Producer(Buffer<Date> server)
    {
        b = server;
    }
    public void run()
    {
        while(true)
        {
            int x = rand.nextInt(5001);
            //int x = rand.nextInt(6);
            System.out.println("Producer napping");
            
            try
            {
                delay.await(x, TimeUnit.MILLISECONDS);
                //delay.await(x, TimeUnit.SECONDS);
            }
            catch(Exception e){}
            Date d = new Date();
            System.out.println("Producer produced " + d);
        
            b.insert(d);
        }
    }
}
