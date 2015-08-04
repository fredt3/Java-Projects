//Fred Tidwell
import java.security.SecureRandom;
public class Bomb 
{

	private String name;
	private int bombNo;
	private int b_Orientation;
	private int[] locationRows = new int[3];
	private int[] locationCols = new int[3];
	private int hitCount;
	private SecureRandom horizOrVert = new SecureRandom();
	private SecureRandom rowGen = new SecureRandom();
	private SecureRandom colGen = new SecureRandom();
	
	public Bomb()
	{
		
	}
	public Bomb(String name, int bombNo)
	{
		this.name = name;
		this.bombNo = bombNo;
		randOrientation();
		hitCount = 0;
		
	}
	public int getHitCount()
	{
		return hitCount;
	}
	public int getBombNo()
	{
		return bombNo;
	}
	public void randOrientation()
	{
		b_Orientation =  horizOrVert.nextInt( 2 );
		if(b_Orientation == 0)
		{
			randHorizLocs();
		}
		else
		{
			randVertLocs();
		}
	}
	public void randHorizLocs()
	{
		locationRows[1] = rowGen.nextInt(6) + 1;
		locationRows[0] = locationRows[1] - 1;
		locationRows[2] = locationRows[1] + 1;
		
		locationCols[0] = colGen.nextInt(8);
		locationCols[1] = locationCols[0];
		locationCols[2] = locationCols[0];
	}
	public void randVertLocs()
	{
		locationCols[1] = colGen.nextInt(6) + 1;
		locationCols[0] = locationCols[1] - 1;
		locationCols[2] = locationCols[1] + 1;
		
		locationRows[0] = rowGen.nextInt(8);
		locationRows[1] = locationRows[0];
		locationRows[2] = locationRows[0];
	}
	public String toString()
	{
		return ("Bomb: " + name + " Number: " + bombNo);
	}
	public void isHit()
	{
		hitCount++;
	}
	public String getName()
	{
		return name;
	}
	public int[] getLocationRows()
	{
		return locationRows;
	}
	public int[] getLocationCols()
	{
		return locationCols;
	}
	public void setLocationRows(int row, int value)
	{
		locationRows[row] = value;
	}
	public void setLocationCols(int col, int value)
	{
		locationCols[col] = value;
	}
	
}
