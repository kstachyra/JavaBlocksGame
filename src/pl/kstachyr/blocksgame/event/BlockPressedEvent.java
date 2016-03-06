package pl.kstachyr.blocksgame.event;

/**zdarzenie polegaj�ce na wci�ni�ciu przycisku myszy przy wskazywaniu na dany klocek,
 * przechowuje wsp�rz�dne wci�ni�tego klocka*/
public class BlockPressedEvent extends GameEvent
{
	/**number kolumny klikni�cia*/
	private int columnNumber;
	/**numer wiersza klikni�cia*/
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
