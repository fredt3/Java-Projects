//Fred Tidwell
import java.security.SecureRandom; 

public class DeckOfCards
{
   private Card[] deck; // array of Card objects

   private int currentCard; // index of next Card to be dealt
   private static final int NUMBER_OF_CARDS = 52; 
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   // constructor fills deck of Cards
   public DeckOfCards( )
   {
	  String[] faces = {  "Ace", "Deuce", "Three", "Four", "Five", 
"Six", "Seven", "Eight", "Nine", 
"Ten", "Jack","Queen", "King"     };
	 	
       String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

       deck = new Card[ NUMBER_OF_CARDS ]; 
      
	  currentCard = 0; //set currentCard so 1st Card dealt is deck[0]

       // populate deck with Card objects
     
 	 for ( int count = 0 ; count < deck.length ; count++ ) 
 	 {
         	deck[count] = new Card( faces[ count % 13 ], suits[ count / 13 ] );
         	deck[count].setFaceVal(count % 13);
         	deck[count].setSuitVal(count / 13);
 	 }
   }

   // shuffle deck of Cards with one-pass algorithm
   public void shuffle()
   {
       // after shuffling, dealing should start at deck[ 0 ] again

       currentCard = 0; // reinitialize currentCard

       // for each Card, pick another random Card and swap them
      
 	 for ( int first = 0 ; first < deck.length ; first++ ) 
      {
         	// select a random number between 0 and 51 
         	int second =  randomNumbers.nextInt( NUMBER_OF_CARDS );
         
         	// swap current Card with randomly selected Card
         	Card temp = deck[ first ];        
         	deck[ first ] = deck[ second ];   
         	deck[ second ] = temp;            
      } 
  } 
  // deal one Card
  public Card dealCard()
   {
      // determine whether Cards remain to be dealt
      if ( currentCard < deck.length )
         	return deck[ currentCard++ ]; //return current Card in array
      else        
         	return null; //return null to indicate that all Cards dealt
   } 
}

