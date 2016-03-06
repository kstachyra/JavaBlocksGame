package pl.kstachyr.blocksgame.defaults;

import pl.kstachyr.blocksgame.model.MyColor;

/**klasa przechowuj�ca wsp�rz�dne (wiersze i kolumny) po�o�enia na planszy i kolor*/
public class CoordinantsAndColor
{
	/**wsp�rz�dna pozioma (kolumna)*/
	public int posX;
	/**wspo�rz�dna pionowa (wiersz)*/
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
