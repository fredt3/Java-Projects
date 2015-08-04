//Fred Tidwell
//Chima Uwazie
public class Semaphore {
    
    private int value;
    
    public Semaphore(int number)
    {
        value = number;
    }
    
    public synchronized void acquire()
    {
        value--;
        if( value <0)
            try {
                wait();
        } catch (Exception e) {}
     
    }         
    public synchronized void release()
    {
        value++;
        if(value <= 0)
            notify();
    }
}
