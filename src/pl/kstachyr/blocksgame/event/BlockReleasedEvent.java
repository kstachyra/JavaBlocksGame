package pl.kstachyr.blocksgame.event;

/**zdarzenie polegaj¹ce na puszczeniu przycisku myszy przy wskazywaniu na dany klocek,
 * przechowuje wspó³rzêdne wciœniêtego klocka*/
public class BlockReleasedEvent extends GameEvent
{
	/**number kolumny klikniêcia*/
	private int columnNumber;
	/**numer wiersza klikniêcia*/
	private int rowNumber;
	
	public BlockReleasedEvent(int columnNumber, int rowNumber)
	{
		super();
		this.columnNumber = columnNumber;
		this.rowNumber = rowNumber;
	}

	/**
	 * @return numer kolumny
	 */
	public int getColumnNumber()
	{
		return columnNumber;
	}

	/**
	 * @return numer wiersza
	 */
	public int getRowNumber()
	{
		return rowNumber;
	}
}
