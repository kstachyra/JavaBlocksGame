package pl.kstachyr.blocksgame.event;

import java.util.Random;

/**zdarzenie oznaczaj�ce rozpocz�cie nowej gry,
 * przechowuje zadane parametry gry*/
public class NewGameEvent extends GameEvent
{
	private int width;
	private int height;
	private int colors;
	private int seed;
	
	/**konstruktor z parametrem seed 
	 * 
	 * @param width szeroko��
	 * @param height wysoko��
	 * @param colors liczba kolor�w
	 * @param seed seed
	 */
	public NewGameEvent(final int width, final int height, final int colors, final int seed)
	{
		super();
		this.width = width;
		this.height = height;
		this.colors = colors;
		this.seed = seed;
	}
	
	/**konstruktor bez paramteru seed (seed ustalany losowo)
	 * 
	 * @param width
	 * @param height
	 * @param colors
	 */
	public NewGameEvent(int width, int height, int colors)
	{
		super();
		this.width = width;
		this.height = height;
		this.colors = colors;
		Random random = new Random();
		this.seed = Math.abs(random.nextInt());
	}

	/**
	 * @return szeroko�� planszy
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @return wysoko�� planszy
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @return liczba kolor�w na planszy
	 */
	public int getColors()
	{
		return colors;
	}

	/**
	 * @return seed planszy
	 */
	public int getSeed()
	{
		return seed;
	}
}