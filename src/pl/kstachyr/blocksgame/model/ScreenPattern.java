package pl.kstachyr.blocksgame.model;


/**
 * klasa stanowi�ca makiet� wysy�an� z modelu do widoku poprzez kontroler,
 * zawiera informacj� o stanie gry, stanie planszy, wyniku, numeru planszy
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