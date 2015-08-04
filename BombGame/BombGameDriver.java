//Fred Tidwell
//To run the debugger... uncomment the two initGrid method calls
//On line 12 and 86 in Grid.java

import java.util.Scanner;

public class BombGameDriver 
{
	
	public static void main(String[] args) 
	{
		Grid grid = new Grid();
		Scanner in = new Scanner(System.in);

		boolean gameOver = false;
		int count = 0;
		while(!gameOver)
		{
			System.out.print("\nEnter a sensor drop point:  ");
			String pos = in.next();
			pos = pos.toUpperCase();
			while((int)pos.charAt(0) < 65 || (int)pos.charAt(0) > 72)
			{
				System.out.println("Enter actual row");
				pos = in.next();
			}
			while((int)pos.charAt(1) < 48 || (int)pos.charAt(1) > 55)
			{
				System.out.println("Enter actual column");
				pos = in.next();
			}
			int row = (int)pos.charAt(0) - 65;
			int col = (int)pos.charAt(1) - 48;
			//System.out.println(row + " " + col);
		
			grid.checkForHit(row, col);
			if(grid.getGameWinner())
			{
				gameOver = true;
			}
			count++;
			if(count == 20)
			{
				gameOver = true;
			}
		}
		
		if(grid.getGameWinner())
		{
			System.out.println("GRID IS CLEARED!\n\nYou used " + count + " sensors. Send in Ledouce.");
		}
		else
		{
			System.out.println("\nYou have used 20 sensors and money is tight.");
			System.out.println("Grid is NOT cleared.");
			System.out.println("Stop with the sensors and send in Chen.");
		}
	}
	
}
