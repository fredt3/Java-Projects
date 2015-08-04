//Fred Tidwell
//Chima Uwazie
public class BoundedBuffer<E> implements Buffer<E>
{
    private static final int BUFFSIZE = 5;
    private E[] buffer;
    private int in;
    private int out;
    private int itemsInBuffer;
    private Semaphore mutex, empty, full;
    public BoundedBuffer()
    {
        in = 0;
        out = 0;
        itemsInBuffer = 0;
        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFSIZE);
        full = new Semaphore(0);
        
        buffer = (E[]) new Object[BUFFSIZE];
    }
    
    @Override
    public void insert(E item)
    {
        empty.acquire();
        mutex.acquire();
        itemsInBuffer++;
        buffer[in] = item;
        in = (in +1) % BUFFSIZE;
        if(itemsInBuffer == 0)
            System.out.println("Producer Entered " + item + " Buffer Size = EMPTY");
        else if(itemsInBuffer == BUFFSIZE)
            System.out.println("Producer Entered " + item + " Buffer Size = FULL");
        else
            System.out.println("Producer Entered " + item + " Buffer Size = " + itemsInBuffer);
        
        
        mutex.release();
        full.release();
    }
    
    @Override
    public E remove()
    {
        E item;
        
        full.acquire();
        mutex.acquire();
        itemsInBuffer--;
        item = buffer[out];
        out = (out + 1) % BUFFSIZE;
        if(itemsInBuffer == 0)
            System.out.println("Consumer consumed " + item + " Buffer Size = EMPTY");
        else if(itemsInBuffer == BUFFSIZE)
            System.out.println("Consumer consumed " + item + " Buffer Size = FULL");
        else
            System.out.println("Consumer consumed " + item + " Buffer Size = " + itemsInBuffer);
        
        mutex.release();
        empty.release();
        return item;
    };
}