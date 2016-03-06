package pl.kstachyr.blocksgame.event;

/**zdarzenie polegaj�ce na puszczeniu przycisku myszy przy wskazywaniu na dany klocek,
 * przechowuje wsp�rz�dne wci�ni�tego klocka*/
public class BlockReleasedEvent extends GameEvent
{
	/**number kolumny klikni�cia*/
	private int columnNumber;
	/**numer wiersza klikni�cia*/
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
