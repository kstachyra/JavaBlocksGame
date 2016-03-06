package pl.kstachyr.blocksgame.defaults;

import java.util.HashSet;
import java.util.Set;

import pl.kstachyr.blocksgame.model.MyColor;

/**sta³e programu*/
public class MyDefaults
{
	/**liczba pikseli na blok*/
	public final static int PIX_PER_BLOCK = 20;
	/**rozmiar planszy standardowej*/
	public final static int BOARD_WIDTH = 25;
	public final static int BOARD_HEIGHT = 10;
	/**liczba kolorów na planszy standardowej*/
	public final static int NUMBER_OF_COLORS = 4;
	/**minimalna liczba usuwanych klocków potrzebna do zdobycia punktów*/
	public static final int MIN_BLOCKS_TO_SCORE = 3;
	/**kara punktowa za usuniêcie pojedynczego klocka*/
	public static final int ONE_BLOCK_PENALTY = 100;
	
	/**rozmiar planszy startowej*/
	public final static int HELLO_WIDTH = 21;
	public final static int HELLO_HEIGHT = 7;
	/** wzór planszy startowej*/
	public final static Set<CoordinantsAndColor> helloScreenSet = new HashSet<CoordinantsAndColor>()
	{
		private static final long serialVersionUID = 1L;
	{
		add(new CoordinantsAndColor(3, 2, MyColor.RED));
		add(new CoordinantsAndColor(3, 3, MyColor.RED));
		add(new CoordinantsAndColor(3, 4, MyColor.RED));
		add(new CoordinantsAndColor(3, 5, MyColor.RED));
		add(new CoordinantsAndColor(3, 6, MyColor.RED));
		add(new CoordinantsAndColor(4, 4, MyColor.RED));
		add(new CoordinantsAndColor(4, 6, MyColor.RED));
		add(new CoordinantsAndColor(5, 4, MyColor.RED));
		add(new CoordinantsAndColor(5, 5, MyColor.RED));
		add(new CoordinantsAndColor(5, 6, MyColor.RED));
		
		add(new CoordinantsAndColor(7, 2, MyColor.YELLOW));
		add(new CoordinantsAndColor(7, 3, MyColor.YELLOW));
		add(new CoordinantsAndColor(7, 4, MyColor.YELLOW));
		add(new CoordinantsAndColor(7, 5, MyColor.YELLOW));
		add(new CoordinantsAndColor(7, 6, MyColor.YELLOW));
		
		add(new CoordinantsAndColor(9, 4, MyColor.PINK));
		add(new CoordinantsAndColor(9, 5, MyColor.PINK));
		add(new CoordinantsAndColor(9, 6, MyColor.PINK));
		add(new CoordinantsAndColor(10, 4, MyColor.PINK));
		add(new CoordinantsAndColor(10, 6, MyColor.PINK));
		add(new CoordinantsAndColor(11, 4, MyColor.PINK));
		add(new CoordinantsAndColor(11, 5, MyColor.PINK));
		add(new CoordinantsAndColor(11, 6, MyColor.PINK));
		
		add(new CoordinantsAndColor(13, 4, MyColor.ORANGE));
		add(new CoordinantsAndColor(13, 5, MyColor.ORANGE));
		add(new CoordinantsAndColor(13, 6, MyColor.ORANGE));
		add(new CoordinantsAndColor(14, 4, MyColor.ORANGE));
		add(new CoordinantsAndColor(14, 6, MyColor.ORANGE));
		add(new CoordinantsAndColor(15, 4, MyColor.ORANGE));
		add(new CoordinantsAndColor(15, 6, MyColor.ORANGE));
		
		add(new CoordinantsAndColor(17, 2, MyColor.GREEN));	
		add(new CoordinantsAndColor(17, 3, MyColor.GREEN));	
		add(new CoordinantsAndColor(17, 4, MyColor.GREEN));	
		add(new CoordinantsAndColor(17, 6, MyColor.GREEN));
		add(new CoordinantsAndColor(18, 2, MyColor.GREEN));	
		add(new CoordinantsAndColor(18, 4, MyColor.GREEN));	
		add(new CoordinantsAndColor(18, 6, MyColor.GREEN));	
		add(new CoordinantsAndColor(19, 2, MyColor.GREEN));
		add(new CoordinantsAndColor(19, 4, MyColor.GREEN));
		add(new CoordinantsAndColor(19, 5, MyColor.GREEN));
		add(new CoordinantsAndColor(19, 6, MyColor.GREEN));
	}};
	
	/**rozmiar planszy koñcowej*/
	public static final int WIN_WIDTH= 20;
	public static final int WIN_HEIGHT = 6;
	/**wzór planszy z napisem "win!" (koñcowej)*/
	public final static Set<CoordinantsAndColor> winScreen = new HashSet<CoordinantsAndColor>()
	{
		private static final long serialVersionUID = 1L;
	{
		add(new CoordinantsAndColor(3, 2, MyColor.GREEN));	
		add(new CoordinantsAndColor(3, 3, MyColor.GREEN));	
		add(new CoordinantsAndColor(3, 4, MyColor.GREEN));	
		add(new CoordinantsAndColor(4, 5, MyColor.GREEN));
		add(new CoordinantsAndColor(5, 4, MyColor.GREEN));	
		add(new CoordinantsAndColor(6, 3, MyColor.GREEN));	
		add(new CoordinantsAndColor(7, 4, MyColor.GREEN));	
		add(new CoordinantsAndColor(8, 5, MyColor.GREEN));
		add(new CoordinantsAndColor(9, 2, MyColor.GREEN));
		add(new CoordinantsAndColor(9, 3, MyColor.GREEN));
		add(new CoordinantsAndColor(9, 4, MyColor.GREEN));
		
		add(new CoordinantsAndColor(11, 2, MyColor.YELLOW));
		add(new CoordinantsAndColor(11, 3, MyColor.YELLOW));
		add(new CoordinantsAndColor(11, 4, MyColor.YELLOW));
		add(new CoordinantsAndColor(11, 5, MyColor.YELLOW));
		
		add(new CoordinantsAndColor(13, 2, MyColor.BROWN));
		add(new CoordinantsAndColor(13, 3, MyColor.BROWN));
		add(new CoordinantsAndColor(13, 4, MyColor.BROWN));
		add(new CoordinantsAndColor(13, 5, MyColor.BROWN));
		add(new CoordinantsAndColor(14, 3, MyColor.BROWN));
		add(new CoordinantsAndColor(15, 4, MyColor.BROWN));
		add(new CoordinantsAndColor(16, 2, MyColor.BROWN));
		add(new CoordinantsAndColor(16, 3, MyColor.BROWN));
		add(new CoordinantsAndColor(16, 4, MyColor.BROWN));
		add(new CoordinantsAndColor(16, 5, MyColor.BROWN));
		
		add(new CoordinantsAndColor(18, 2, MyColor.RED));
		add(new CoordinantsAndColor(18, 3, MyColor.RED));
		add(new CoordinantsAndColor(18, 5, MyColor.RED));
	}};
}