package pl.kstachyr.blocksgame.model;


/**
 * klasa stanowi¹ca makietê wysy³an¹ z modelu do widoku poprzez kontroler,
 * zawiera informacjê o stanie gry, stanie planszy, wyniku, numeru planszy
 */
public class ScreenPattern
{
	public Block[][] board;
	public int score;
	public String gameCode;
	public int boardHeight;
	public int boardWidth;
	public Status status;
}