package pl.kstachyr.blocksgame.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import pl.kstachyr.blocksgame.defaults.CoordinantsAndColor;
import pl.kstachyr.blocksgame.defaults.MyDefaults;

/**
 * plansza gry
 * zawiera tablic� klock�w,
 * ma informacj� o wysoko�ci, szeroko�ci, punktacji rozgrywki, pozosta�ych klockach i parametrach planszy, obecnie zaznaczonych klockach,
 * realizuje operacje na klockach, dba o poprawno�� danych -> stanowi mechanik� rozgrywki
 */
public class Board
{
	/**plansza - tablica klock�w*/
	private Block[][] blocks;
	/**wysoko�c planszy z barierami*/
	private final int height;
	/**szeroko�� planszy z baierami*/
	private final int width;
	/**numer planszy (�r�d�o generatora)*/
	private final int seed;
	/**liczba klock�w pozosta�ych na planszy*/
	private int blocksRemaining;
	/**aktualna liczba uzyskanych punkt�w*/
	private int score;
	/**zbi�r zanaczonych klock�w*/
	private Set<Block> markedBlocks = new HashSet<Block>();
	/**liczba u�ywanych kolor�w (gdy wi�ksza ni� zdefiniowane, korzysta tylko ze zdefiniowanych*/
	private int numberOfColors = MyDefaults.NUMBER_OF_COLORS;
	
	/**
	 * konstruktor klasy Board bez parametr�w
	 * wykorzystywany na pocz�tku programu, przed wygenerowaniem planszy i rozpoczeciem gry
	 */
	public Board()
	{
		height = MyDefaults.HELLO_HEIGHT+2;
		width = MyDefaults.HELLO_WIDTH+2;
		seed = 0;
		score = 0;
		blocks = new Block[width][height];
		drawHelloScreen();
	}
	
	/**
	 * tworzenie nowej planszy
	 * ustawia pola width i height klasy Board, tworzy tablice klock�w, wywo�uje fill() w celu zape�nienia planszy klockami
	 * 
	 * @param width ustawiana szerokosc planszy
	 * @param height ustawiana wysokosc planszy 
	 * @param seed seed planszy
	 */
	public Board(final int width, final int height, final int colors, final int seed)
	{
		this.height = height+2;
		this.width = width+2;
		this.seed = seed;
		this.numberOfColors = colors;
		blocksRemaining = 0;
		blocks = new Block[this.width][this.height];
		fill();
	}
	
	/**
	 * tworzy plansze o podanych rozmiarach bez zape�niania jej klockami
	 *
	 * @param width szeroko�� planszy
	 * @param height wysoko�� planszy
	 */
	public Board(final int width, final int height)
	{
		this.height = height+2;
		this.width = width+2;
		this.seed = 0;
		this.numberOfColors = 0;
		blocksRemaining = 0;
		blocks = new Block[this.width][this.height];
	}
	
	/**
	 * rysuje ekran startowy (konieczna jest plansza o odpowiednich rozmiarach)
	 */
	void drawHelloScreen()
	{
		for (int i=0; i<width; ++i)
		{
			for (int j=0; j<height; ++j)
			{
				blocks[i][j] = new Block(MyColor.BARRIER);
			}
		}

		for (Iterator<CoordinantsAndColor> iterator = MyDefaults.helloScreenSet.iterator(); iterator.hasNext();)
		{
		    CoordinantsAndColor helloBlock = iterator.next();
		    blocks[helloBlock.posX][helloBlock.posY] = new Block(helloBlock.color);
		    ++blocksRemaining;
		}
	}
	
	/**
	 * rysuje ekran ko�cowy (konieczna jest plansza o odpowiednich rozmiarach)
	 */
	void drawWinScreen()
	{
		for (int i=0; i<width; ++i)
		{
			for (int j=0; j<height; ++j)
			{
				blocks[i][j] = new Block(MyColor.BARRIER);
			}
		}

		for (Iterator<CoordinantsAndColor> iterator = MyDefaults.winScreen.iterator(); iterator.hasNext();)
		{
		    CoordinantsAndColor winBlock = iterator.next();
		    blocks[winBlock.posX][winBlock.posY] = new Block(winBlock.color);
		    ++blocksRemaining;
		}
	}
	
	/**
	 * zape�nia plansz� klockami
	 */
	private void fill()
	{
		Random randNum = new Random();
		randNum.setSeed(seed);
		for (int i=1; i<width-1; ++i)
		{
			for (int j=1; j<height-1; ++j)
			{
				blocks[i][j] = new Block(MyColor.getSeedColor(randNum.nextLong(), numberOfColors));
			}
		}
		for (int i=0; i<width; ++i)
		{
			blocks[i][0] = new Block(MyColor.BARRIER);
			blocks[i][height-1] = new Block(MyColor.BARRIER);
			
		}
		for (int i=0; i<height; ++i)
		{
			blocks[0][i] = new Block(MyColor.BARRIER);
			blocks[width-1][i] = new Block(MyColor.BARRIER);
		}
		blocksRemaining = (width-2)*(height-2);
	}
	
	/**
	 * metoda startowa rekurencyjnego zaznaczania wskazanej gruby klock�w
	 * 
	 * @param columnNumber numer kolumny klocka
	 * @param rowNumbernumer wiersza klocka
	 */
	void markGroupOfBlocks(final int columnNumber, final int rowNumber)
	{
		final boolean[][] isVisited = new boolean[width][height];
		if (!blocks[columnNumber][rowNumber].isBarrier())
		{
			markBlock(columnNumber, rowNumber, isVisited);
		}
	}
	
	/**
	 * zaznacza wskazany klocek i wywo�uje sam� siebie dla s�siednich o takim samym kolorze
	 * 
	 * @param columnNumber numer kolumny klocka
	 * @param rowNumber numer wiersza klocka
	 * @param isVisited tablica odwiedzonych klock�w
	 */
	private void markBlock(final int columnNumber, final int rowNumber, final boolean[][] isVisited)
	{
		if (!isVisited[columnNumber][rowNumber])
		{
			isVisited[columnNumber][rowNumber] = true;
			blocks[columnNumber][rowNumber].setMarked(true);
			markedBlocks.add(blocks[columnNumber][rowNumber]);
			
			if (blocks[columnNumber-1][rowNumber].getColor() == blocks[columnNumber][rowNumber].getColor())
			{
				markBlock(columnNumber-1, rowNumber, isVisited);
			}
			if (blocks[columnNumber+1][rowNumber].getColor() == blocks[columnNumber][rowNumber].getColor())
			{
				markBlock(columnNumber+1, rowNumber, isVisited);
			}
			if (blocks[columnNumber][rowNumber-1].getColor() == blocks[columnNumber][rowNumber].getColor())
			{
				markBlock(columnNumber, rowNumber-1, isVisited);
			}
			if (blocks[columnNumber][rowNumber+1].getColor() == blocks[columnNumber][rowNumber].getColor())
			{
				markBlock(columnNumber, rowNumber+1, isVisited);
			}
		}
	}
	
	/**
	 * decyduje, czy przekaza� zaznaczone klocki do usuni�cia czy je odznaczy�, zlicza punkty przy usuwaniu
	 * 
	 * @param columnNumber numer kolumny klocka
	 * @param rowNumber numer wiersza klocka
	 */
	void unmarkOrDelete(final int columnNumber, final int rowNumber)
	{
		if (blocks[columnNumber][rowNumber].isMarked())
		{
			for (Iterator<Block> iterator = markedBlocks.iterator(); iterator.hasNext();)
			{
			    Block element = iterator.next();
				element.setToDelete(true);
			}
			blocksRemaining = blocksRemaining - markedBlocks.size();
			updateScore(markedBlocks.size());
			markedBlocks.clear();
			deleteBlockstoDelete();
		}
		else
		{
			unmarkAllBlocks();
		}
	}

	/**
	 * odznacza wszystkie zaznaczone klocki
	 */
	void unmarkAllBlocks()
	{
		for (Iterator<Block> iterator = markedBlocks.iterator(); iterator.hasNext();)
		{
		    Block element = iterator.next();
			element.setMarked(false);
		}
		markedBlocks.clear();
	}
	
	/**
	 * usuwa klocki zaznaczone do usuni�cia,
	 * wysy�a piony z tablicy do kolumny-listy i otrzymuje nowy pion dla tablicy
	 */
	private void deleteBlockstoDelete()
	{
		for (int i=1; i<width-1; ++i)
		{
			Column column = new Column();
			for (int j=height-2; j>0; --j)
			{
				column.push(blocks[i][j]);
			}
			column.deleteBlocks();
			for (int j=height-2; j>0; --j)
			{
				blocks[i][j] = column.pop();
			}
		}
	}
	
	/**
	 * aktualizuje aktualn� punktacj�
	 * 
	 * @param blocksDeleted liczba usuwanych klock�w
	 */
	private void updateScore(final int blocksDeleted)
	{
		if (blocksDeleted>=MyDefaults.MIN_BLOCKS_TO_SCORE)
		{
			score = score + blocksDeleted*(blocksDeleted/2);
		}
		if (blocksDeleted == 1)
		{
			score = score - MyDefaults.ONE_BLOCK_PENALTY;
		}
	}

	/**
	 * @return referencja na tablic� klock�w - plansz�
	 */
	public Block[][] getBlocks()
	{
		return blocks;
	}
	
	/**
	 * @return szeroko�� planszy bez barier
	 */
	public int getWidth()
	{
		return width-2;
	}

	/**
	 * @return wysoko�� planszy bez barier
	 */
	public int getHeight()
	{
		return height-2;
	}
	
	/**
	 * @return numer planszy (seed)
	 */
	public int getSeed()
	{
		return seed;
	}

	/**
	 * ustawia liczb� pozosta�ych klock�w na zero
	 */
	public void zeroBlocksRemaining()
	{
		blocksRemaining = 0;
	}

	/**
	 * @return pozosta�e bloki na planszy
	 */
	public int getBlocksRemaining()
	{
		return blocksRemaining;
	}

	/**
	 * @return liczba kolor�w mo�liwych na danej planszy
	 */
	public int getNumberOfColors()
	{
		return numberOfColors;
	}

	/**
	 * @return aktualna punktacja
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * @param aktualna punktacja do ustawienia
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
}