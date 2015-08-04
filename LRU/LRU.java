//Calculates amount of random page faults given number of
//page frames and reference string size with a Least Recently
//Used System
//
//Fred Tidwell
//Kyle Thompson
package lru;
import java.security.SecureRandom;
public class LRU 
{
    //insert number of page frames
    public static int numPageFrames = 5;
    
    //global variables
    public static SecureRandom seed = new SecureRandom();
    public static int[] frame = new int[numPageFrames];
    public static int frameRepInd = 0;
    public static void main(String[] args) 
    {      
            //Populate initial frame with unusuable page number
           for(int i = 0; i < frame.length; i++)
           {
               frame[i] = -1;
           }
           int pageFaults = 0;
           int[] refString = new int[50];
           System.out.print("Reference String: ");
           //populate reference string
           for(int i = 0; i < refString.length; i++)
           {
               refString[i] = seed.nextInt(10);
               System.out.print(refString[i] + " ");
               
               //Calculate Page faults
               pageFaults += LRUinsert(i, refString);
           }
           
           //Print some useful data
           System.out.println("");
           System.out.println("Number of Page Faults: " + pageFaults);
           System.out.print("Last Frame: ");
           for(int i = 0; i < frame.length; i++)
           {
               System.out.print(frame[i] + " ");
           }
    }
    public static int LRUinsert(int index, int[] refS)
    {
        //in buffer
        for(int i = 0; i < frame.length; i++)
        {
            if(frame[i] ==  refS[index])
            {
                return 0;
            }
        }
        
        //unused spot
        for(int i = 0; i < frame.length; i++)
        {
            if(frame[i] == -1)
            {
                frame[i] = refS[index];
                return 1;
            }
        }
        
        //least recently used
        int[] lastTimeUsed = new int[frame.length];
        
        //count distance away from index
        for(int i = 0; i < index; i++)
        {
            for(int j = 0; j < frame.length; j++)
            {
                if(frame[j] ==  refS[i])
                {
                    lastTimeUsed[j] = index - i;
                }
            }
        }
       
        //find max distance away and replace that one
        int maxV = max(lastTimeUsed);
        for(int i = 0; i < frame.length; i++)
        {
            if(lastTimeUsed[i] == maxV)
            {
                frame[i] = refS[index];
                return 1;
            }
        }
        
        return 1;    
    }
    
    //return max
    public static int max(int[] x)
    {
        int rVal = 0;
        for(int i = 0; i < x.length; i++)
        {
            if(x[i] > rVal)
            {
                rVal = x[i];
            }
        }
        return rVal;
    }
}
