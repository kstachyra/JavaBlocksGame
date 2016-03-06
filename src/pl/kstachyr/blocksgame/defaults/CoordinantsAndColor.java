package pl.kstachyr.blocksgame.defaults;

import pl.kstachyr.blocksgame.model.MyColor;

/**klasa przechowuj¹ca wspó³rzêdne (wiersze i kolumny) po³o¿enia na planszy i kolor*/
public class CoordinantsAndColor
{
	/**wspó³rzêdna pozioma (kolumna)*/
	public int posX;
	/**wspo³rzêdna pionowa (wiersz)*/
	public int posY;
	/**kolor*/
	public MyColor color;
	
	/**
	 * konstruktor CoordinantsAndColor
	 * 
	 * @param x numer kolumny
	 * @param y numer widoku
	 * @param color kolor
	 */
	CoordinantsAndColor(final int x, final int y, final MyColor color)
	{
		posX = x;
		posY = y;
		this.color = color;
	}
}
