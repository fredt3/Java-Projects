//Fred Tidwell
//Chima Uwazie
import java.util.Date;
import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class Barber implements Runnable
{
    private Buffer<Integer> b;
    SecureRandom rand = new SecureRandom();
    CountDownLatch delay = new CountDownLatch(1);
    public Barber(Buffer<Integer> server)
    {
        b = server;
    }
    public void run()
    {
        while(true)
        {
            b.remove();
        }
    }
}
