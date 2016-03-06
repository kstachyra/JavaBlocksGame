package pl.kstachyr.blocksgame.model;

import java.util.Random;

/**
 * typ wyliczeniowy - kolor dla klock�w,
 * ostatni to kolor klocka bariery!
 */
public enum MyColor
{
	RED(204, 0, 0), YELLOW(181, 181, 0), GREEN(0, 153, 0), BROWN(153, 76, 0), BLUE(0, 76, 153), PINK(204, 0, 102),
	GRAY(96, 96, 96), ORANGE(182, 128, 0), BARRIER(0, 0, 0);
	
	/**sk�adowa czarwona koloru*/
	int r;
	/**sk�adowa zielona koloru*/
	int g;
	/**sk�adowa niebieska koloru*/
	int b;
		
	/**
	 * konstruktor koloru
	 * 
	 * @param r sk�adowa czerwona koloru
	 * @param g sk�adowa zielona koloru
	 * @param b sk�adowa niebieska koloru
	 */
	MyColor(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * generuje losowa wartosc koloru na podstawie seed,
	 * z po�r�d pierwszych numOfColors kolor�w,
	 * nie generuje koloru bariery,
	 * w przypadku podania wi�kszej liczby kolor�w ni� dost�pna, ogranicza j� do najwi�jszej mo�liwej
	 * 
	 * @return kolor wygenerowany przez seed
	 * @param seed seed generatora
	 * @param numOfColors liczba kolor�w mo�liwych do wygenerowania
	 * @return wylosowany kolor
	 */
	public static MyColor getSeedColor(final long seed, int numOfColors)
	{
		if (numOfColors > values().length-1)
		{
			numOfColors = values().length-1;
		}
		Random random = new Random();
		random.setSeed(seed);
		return values()[random.nextInt(numOfColors)];
	}
}