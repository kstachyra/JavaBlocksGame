package pl.kstachyr.blocksgame.event;

/**zdarzenie polegaj¹ce na wciœniêciu przycisku myszy przy wskazywaniu na dany klocek,
 * przechowuje wspó³rzêdne wciœniêtego klocka*/
public class BlockPressedEvent extends GameEvent
{
	/**number kolumny klikniêcia*/
	private int columnNumber;
	/**numer wiersza klikniêcia*/
	private int rowNumber;
	
	public BlockPressedEvent(final int columnNumber, final int rowNumber)
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
