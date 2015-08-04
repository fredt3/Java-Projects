//Fred Tidwell
public class Card 
{
   private String face; // face of card ("Ace", "Deuce", ...)
   private String suit; // suit of card ("Hearts", "Diamonds", ...)
   private int faceVal;
   private int suitVal;
   // two-argument constructor initializes card's face and suit
   public Card( String cardFace, String cardSuit )
   {
      face = cardFace; // initialize face of card
      suit = cardSuit; // initialize suit of card
   } 

   // return String representation of Card
   public String toString() 
   { 
      return face + " of " + suit;
   } 
   
   public int getFaceVal()
   {
	   return faceVal;
   }
   public void setFaceVal(int faceNo)
   {
	   faceVal = faceNo;
   }
   public int getSuitVal()
   {
	   return suitVal;
   }
   public void setSuitVal(int suitNo)
   {
	   suitVal = suitNo;
   }
} 
