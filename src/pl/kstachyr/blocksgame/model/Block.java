package pl.kstachyr.blocksgame.model;

/**
 * klocek, podstawowy element na planszy
 * zawiera kolor i informacjê, czy nale¿y go usun¹æ oraz czy jest zaznaczony (nale¿y do zaznaczonej grupy)
 */
public class Block
{
	/**kolor klocka*/
	private MyColor color;
	/**czy jest przeznaczony do usuniêcia*/
	private boolean toDelete;
	/**czy jest zaznaczony (nale¿y do wskazanej grupy)*/
	private boolean marked;

	/**
	 * konstruktor klasy Block - pojedynczego klocka
	 * pole toDelete domyœlnie równe false
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
	 * @return mój kolor
	 */
	public MyColor getColor()
	{
		return color;
	}
	
	/**
	 * @return sk³adowa r koloru
	 */
	public int getColorR()
	{
		return color.r;
	}
	
	/**
	 * @return sk³adow¹ g koloru
	 */
	public int getColorG()
	{
		return color.g;
	}
	
	/**
	 * @return sk³adow¹ b koloru
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
	 * @param ustawiana wartosæ pola marked (zanaczony)
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