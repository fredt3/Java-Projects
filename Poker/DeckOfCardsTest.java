//Fred Tidwell
public class DeckOfCardsTest
{
	public static final int HAND_SIZE = 5;
   public static void main( String[] args )
   {
	DeckOfCards myDeckOfCards = new DeckOfCards();
    myDeckOfCards.shuffle(); // place Cards in random order
	
	String LH = "Left Hand:";
	String RH = "Right Hand:";
	Card [] leftHand = new Card[HAND_SIZE];
	Card [] rightHand = new Card[HAND_SIZE];
	
	System.out.printf( "%-19s", LH );
	System.out.printf( "%-19s%n", RH );
	
	for(int i = 0; i < 5; i++)
	{
		leftHand[i] = myDeckOfCards.dealCard();
		rightHand[i] = myDeckOfCards.dealCard(); 
		System.out.printf( "%-19s", leftHand[i] );
		System.out.printf( "%-19s%n", rightHand[i] );
	}
	System.out.println();
	double lhEval = evaluateHand(leftHand);
	double rhEval = evaluateHand(rightHand);
	String lh_string = get_handType(lhEval);
	String rh_string = get_handType(rhEval);
	
	System.out.println("Hand Values:");
	System.out.printf( "%-19s", lh_string );
	System.out.printf( "%-19s%n", rh_string );
	String winner = "";
	
	if(lhEval > rhEval)
	{
		winner = "the left hand is better";
	}
	if(rhEval > lhEval)
	{
		winner = "the right hand is better";
	}
	
	if(lh_string.equals(rh_string))
	{
		int i_winner = findHighCard(leftHand, rightHand);
		if(i_winner == 1)
		{
			winner = "the left hand is better";
		}
		if(i_winner == 2)
		{
			winner = "the right hand is better";
		}
		if(i_winner == 0)
		{
			winner = "the hands are tied";
		}
	}
	System.out.println(winner);
   } 

private static String get_handType(double handNo) {
	   String handType = "";
	   
	   switch ((int) handNo) 
	   {
       case 1:  handType = "Pair";
                break;
       case 2:  handType = "Two Pairs";
                break;
       case 3:  handType = "Three of a kind";
                break;
       case 4:  handType = "Flush";
                break;
       case 5:  handType = "Straight";
                break;
       case 6:  handType = "Full House";
                break;
       case 7:  handType = "Four of a Kind";
                break;
       default: handType = "None";
                break;
	   }
	return handType;
}
private static int findHighCard(Card[] hand1, Card[] hand2) 
   {
	   int [] faces1 = sortFaces(getFaceValues(hand1));
	   int [] faces2 = sortFaces(getFaceValues(hand2));
	   
	   //straights and flushes
	   if(straight(faces1) || evaluateHand(hand1) == 4 || evaluateHand(hand2) == 4 || evaluateHand(hand1) == 0)
	   {
		   for(int i = 4; i >= 0; i++)
		   {
			   if(faces1[i] > faces2[i])
			   {
				   return 1;
			   }
			   if(faces2[i] > faces1[i])
			   {
				   return 2;
			   }
	   		}
	   }
	   
	   //All of the others
	   int [] sortFreqFaces1 = sortByFreq(faces1);
	   int [] sortFreqFaces2 = sortByFreq(faces2);	   
	   for(int i = 4; i >= 0; i++)
	   {
		   if(sortFreqFaces1[i] > sortFreqFaces2[i])
		   {
			   return 1;
		   }
		   if(sortFreqFaces2[i] > sortFreqFaces1[i])
		   {
			   return 2;
		   }
   		}	   
	return 0;
   }
private static int[] sortByFreq(int[] faces) 
{
	int [] freqFaces = getFreq(faces);
	//BubbleSort
	for (int i = 0;i < freqFaces.length-1; i++)
	{
        for (int j = i+1; j<freqFaces.length; j++)
        {
        	if (freqFaces[i] > freqFaces[j])   
            {   
        		int temp = faces[i];
                faces[i] = faces[j];
                faces[j] = temp;
            }
        }
        
	}     
	
	
	return faces;
   }
public static int[] getFaceValues(Card[] hand)
   {
	   int [] faces = {0,0,0,0,0};
	   for(int i = 0; i < hand.length; i++)
	   {
		   faces[i] = hand[i].getFaceVal();
		   if(faces[i] == 0)
		   {
			   faces[i] = 13;
		   }
	   }
	   return faces;
   }
   public static int[] getSuitValues(Card[] hand)
   {
	   int [] suits = {0,0,0,0,0};
	   for(int i = 0; i < hand.length; i++)
	   {
		   suits[i] = hand[i].getSuitVal();
	   }
	   return suits;
   }
   public static double evaluateHand(Card[] hand)
   {
	   //String handState = "";
	   double handVal = 0;
	   
	   int [] faces = sortFaces(getFaceValues(hand));
	   int [] suits = getSuitValues(hand); 
	   
	   int [] faceFreqs = getFreq(faces);
	   int [] suitFreqs = getFreq(suits);
	   //faces = sortFaces(faces);
	   int maxFace = faces[4];
	   //System.out.println(maxFace);
	   
	   //Handle 4ofakind, 3ofakind, and pair
	   for(int i = 0; i < 5; i++)
	   {
		   if(faceFreqs[i] == 4)
		   {
			   handVal = 7;
		   }
		   if(faceFreqs[i] == 3 && handVal < 3)
		   {
			   handVal = 3;
		   }
		   if(faceFreqs[i] == 2 && handVal < 1)
		   {
			   
			   handVal = 1;
		   }
		   if(suitFreqs[i] == 5 && handVal < 4)
		   {
			   handVal = 4;
		   }
	   }
	   int count2Pair = 0;
	   int countFullHouse = 0;
	   for(int i = 0; i < 5; i++)
	   {
		   if(faceFreqs[i] == 2)
		   {
			   count2Pair++;
		   }
		   if(faceFreqs[i] == 3)
		   {
			   countFullHouse++;
		   }
	   }
	   if(count2Pair == 4 && handVal < 2)
	   {
		   handVal = 2;
	   }
	   if(count2Pair == 2 && countFullHouse == 3 && handVal < 6)
	   {
		   handVal = 6;
	   }
	   
	   if(straight(faces) && handVal < 5)
	   {
		   handVal = 5;
	   }
	   
	
	return handVal;
   }
   public static boolean straight(int[] sFaces) 
   {
	   boolean isStraight = false;
	   for(int i = 0; i < 4; i++)
	   {
		   if(sFaces[i] + 1 == sFaces[i+1])
		   {
			   isStraight = true;
		   }
		   else
		   {
			   return false;
		   }
	   }
	   
	   return isStraight;
   }
public static int[] sortFaces(int[] faces) 
   {
	//BubbleSort
	for (int i = 0;i < faces.length-1; i++)
	{
        for (int j = i+1; j<faces.length; j++)
        {
        	if (faces[i] > faces[j])   
            {   
        		int temp = faces[i];
                faces[i] = faces[j];
                faces[j] = temp;
            }
        }
	}     
	return faces;
   }

   public static int[] getFreq(int[] faces)
   {
	   int [] freq = {0,0,0,0,0};
	   for(int i = 0; i < faces.length; i++)
	   {
		   for(int j = 0; j < faces.length; j++)
		   {
			   
			   if(faces[i] == faces[j])
			   {
				   freq[i]++;
			   }  
		   }
	   }
	  
	return freq;
   }
}      
