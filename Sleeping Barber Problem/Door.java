//Fred Tidwell
//Chima Uwazie
import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class Door implements Runnable
{
    
    private Buffer<Integer> b;
    public SecureRandom rand = new SecureRandom();
    public CountDownLatch delay = new CountDownLatch(1);
    public int number = 0;
    SharedVariables SV = new SharedVariables();
    public Door(Buffer<Integer> server)
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
            //Date d = new Date();
            //System.out.println("Producer produced " + d);
        
            number = SV.numCustomers;
            SV.numCustomers += 1;
            b.insert(number);
        }
    }
}
