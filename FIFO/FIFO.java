//Calculates amount of random page faults given number of
//page frames and reference string size with a First In
//First Out system
//
//Fred Tidwell
//Kyle Thompson
package fifo;
import java.security.SecureRandom;
public class FIFO 
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
               pageFaults += FIFOinsert(i, refString);
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
    public static int FIFOinsert(int index, int[] refS)
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
        
        //replace first in
        frame[frameRepInd] = refS[index];
        frameRepInd = (frameRepInd +1) % frame.length;
        
        return 1;    
    }
}
