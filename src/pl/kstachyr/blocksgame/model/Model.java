package pl.kstachyr.blocksgame.model;

import java.util.Random;

import pl.kstachyr.blocksgame.defaults.MyDefaults;

/**
 * model z MVC,
 * implementuje rozgrywk� og�lnie (stan gry, nowa gra, koniec)
 * (model.board jest odpowiedzialny za sam� mechanik� pojedy�czej rozgrywki)
 */
public class Model
{
	/**plansza gry*/
	public Board board;
	/**obecny status gry - enum START, PLAYING, END*/
	private Status status;
	
	/**
	 * konstruktor modelu
	 */
	public Model()
	{
		status = Status.START;
		board = new Board();
	}

	/**
	 * uruchamia now� gr� z podanymi parametrami,
	 * tworzy plansze, zmienia status
	 */
	public void newGame(final int width, final int height, final int colors, final int seed)
	{
		status = Status.PLAYING;
		board = new Board(width, height, colors, seed);
	}
	
	/**
	 * restartuje gr� z takimi samymi parametrami, jakie by�y poprzednie, je�li jest w trakcie rozgrywki
	 * (w innym przypadku ustawia hello screen)
	 */
	public void restartGame()
	{
		board.zeroBlocksRemaining();
		if(status == Status.PLAYING)
		{
			board = new Board(board.getWidth(), board.getHeight(), board.getNumberOfColors(), board.getSeed());
		}
		else
		{
			board = new Board(MyDefaults.HELLO_WIDTH, MyDefaults.HELLO_HEIGHT);
			board.drawHelloScreen();
		}
	}
	
	/**
	 * ustawia status na END, rysuje win screen
	 */
	public void endGame()
	{
		status = Status.END;
		int score = board.getScore();
		board = new Board(MyDefaults.WIN_WIDTH, MyDefaults.WIN_HEIGHT);
		board.setScore(score);
		board.drawWinScreen();
	}
		
	/**
	 * generuje i zwraca makiet� dla widoku
	 * 
	 * @return makieta dla widoku
	 */
	public ScreenPattern getScreenPattern()
	{
		ScreenPattern sp = new ScreenPattern();
		sp.board = board.getBlocks();
		sp.boardHeight = board.getHeight();
		sp.boardWidth = board.getWidth();
		
		if(status == Status.PLAYING)
		{
			sp.gameCode = Long.toString(board.getSeed()) +"S" + Integer.toString(board.getHeight()) + "X" + Integer.toString(board.getWidth());
		}
		else
		{
			sp.gameCode = "BlockS";
		}
		sp.score = board.getScore();	
		sp.status = status;
		return sp;
	}
	
	/**
	 * wywo�uje metod� klasy board do zaznaczenia grupy klocka o podanych wsp�rz�dnych
	 * 
	 * @param columnNumber numer kolumny
	 * @param rowNumber numer wiersza
	 */
	public void markGroupOfBlocks(final int columnNumber, final int rowNumber)
	{
		board.markGroupOfBlocks(columnNumber, rowNumber);
	}
	
	/**
	 * wywo�uje metod� klasy board do odznaczenia lub usuni�cia grupy klock�w,
	 * wywo�uje metod� whenBoardCleared() w celu sprawdzenia i ewentualnego obs�u�enia sytuacji, gdy nie ma ju� klock� na planszy
	 * 
	 * @param columnNumber numer kolumny
	 * @param rowNumber numer wiersza
	 */
	public void unmarkOrDelete(final int columnNumber, final int rowNumber)
	{
		board.unmarkOrDelete(columnNumber, rowNumber);
		whenBoardCleared();
	}
	
	
	/**
	 * wywo�yje metod� klasy board do odznaczenia wszystkich zaznaczonych klock�w
	 */
	public void unmarkAllBlocks()
	{
		board.unmarkAllBlocks();
	}

	/**
	 * sprawdza warunki wygranej,
	 * lub przej�cia z innych stan�w do stanu PLAYING
	 */
	public void whenBoardCleared()
	{
		if(board.getBlocksRemaining() == 0)
		{
			if (status == Status.PLAYING)
			{
				endGame();
			}
			else
			{
				Random random = new Random();
				newGame(MyDefaults.BOARD_WIDTH, MyDefaults.BOARD_HEIGHT, MyDefaults.NUMBER_OF_COLORS, Math.abs(random.nextInt()));
			}
		}
	}
}