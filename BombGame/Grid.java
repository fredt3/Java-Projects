//Fred Tidwell
public class Grid {

	public static final int ARRAY_SIZE = 8;
	private int[][] gridMat = new int[ARRAY_SIZE][ARRAY_SIZE];
	private Bomb[] bombs = new Bomb[3];
	private boolean gameWinner = false;
	private int totalHitCount = 0;
	public Grid()
	{
		initBombs();
		//initGrid();
	}
	public void initGrid()
	{
		System.out.println("  0 1 2 3 4 5 6 7");
		char row = 'A';
		for(int i = 0; i < ARRAY_SIZE; i++)
		{
			
			System.out.print((char)(row+i) + " ");
			for(int j = 0; j < ARRAY_SIZE; j++)
			{
				System.out.print(gridMat[i][j] + " ");
			}
			System.out.println();
		}	
	}
	public void initBombs()
	{
		
		bombs[0] = new Bomb("Chemical Bomb", 1);
		bombs[1] = new Bomb("Radiological Bomb", 2);
		bombs[2] = new Bomb("Bomb Bomb", 3);
		
		for(int i = 0; i < 3; i++)
		{
			markBombs(bombs[i]);
		}
	}
	public void markBombs(Bomb bomb)
	{
		System.out.println(bomb);
		for(int i = 0; i < 3; i++)
		{
			gridMat[bomb.getLocationRows()[i]][bomb.getLocationCols()[i]] = bomb.getBombNo();
		}
	}
	public void checkForHit(int row, int col)
	{
		boolean b_hit = false;
		int [] bombNoMarked= {0, 0, 0};
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(row == bombs[i].getLocationRows()[j] && col == bombs[i].getLocationCols()[j] )
				{
					
					gridMat[row][col] = 9;
					bombs[i].setLocationRows(j, -1);
					bombs[i].setLocationCols(j, -1);
					bombs[i].isHit();
					b_hit = true;
					
					if(bombs[i].getHitCount() == 3)
					{
						totalHitCount++;
						bombNoMarked[i] = 1;
					}
				}
			}
			
		}
		
		if(!b_hit)
		{
			System.out.print("Miss.");
		}
		else
		{
			//initGrid();
			System.out.print("Hit. ");
		}
		
		for(int i = 0; i < 3; i++)
		{
			if(bombNoMarked[i] != 0)
			{
				System.out.print(bombs[i].getName() + " Marked!\n");
			}
		}
		
		if(totalHitCount == 3)
		{
			gameWinner = true;
		}
		
		System.out.println();
		
	}
	public boolean getGameWinner()
	{
		return gameWinner;
	}
}
