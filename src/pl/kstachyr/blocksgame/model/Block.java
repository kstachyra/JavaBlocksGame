package pl.kstachyr.blocksgame.model;

/**
 * klocek, podstawowy element na planszy
 * zawiera kolor i informacj�, czy nale�y go usun�� oraz czy jest zaznaczony (nale�y do zaznaczonej grupy)
 */
public class Block
{
	/**kolor klocka*/
	private MyColor color;
	/**czy jest przeznaczony do usuni�cia*/
	private boolean toDelete;
	/**czy jest zaznaczony (nale�y do wskazanej grupy)*/
	private boolean marked;

	/**
	 * konstruktor klasy Block - pojedynczego klocka
	 * pole toDelete domy�lnie r�wne false
	 * 
	 * @param color
	 */
	public Block(final MyColor color)
	{
		this.setColor(color);
		this.toDelete = false;
		this.setMarked(false);
	}

	/**
	 * @return m�j kolor
	 */
	public MyColor getColor()
	{
		return color;
	}
	
	/**
	 * @return sk�adowa r koloru
	 */
	public int getColorR()
	{
		return color.r;
	}
	
	/**
	 * @return sk�adow� g koloru
	 */
	public int getColorG()
	{
		return color.g;
	}
	
	/**
	 * @return sk�adow� b koloru
	 */
	public int getColorB()
	{
		return color.b;
	}

	/**
	 * @param color kolor do ustawienia
	 */
	public void setColor(final MyColor color)
	{
		this.color = color;
	}

	/**
	 * @return pole toDelete
	 */
	public boolean isToDelete()
	{
		return toDelete;
	}

	/**
	 * @param wartosc pola do ustawienia toDelete
	 */
	public void setToDelete(final boolean toDelete)
	{
		this.toDelete = toDelete;
	}

	/**
	 * @return czy zaznaczony
	 */
	public boolean isMarked()
	{
		return marked;
	}

	/**
	 * @param ustawiana wartos� pola marked (zanaczony)
	 */
	public void setMarked(final boolean marked)
	{
		this.marked = marked;
	}
	
	/**
	 * @return czy klocek jest klockiem bariery
	 */
	public boolean isBarrier()
	{
		if(getColor() == MyColor.BARRIER)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}