package pl.kstachyr.blocksgame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

import pl.kstachyr.blocksgame.defaults.MyDefaults;
import pl.kstachyr.blocksgame.event.*;
import pl.kstachyr.blocksgame.model.Block;

/**
 * klasa odzwierciedlaj¹ca tê czêœæ widoku, na której znajduj¹ siê klocki, na której toczy siê w³aœciwa gra
 */
public class GamePanel extends JPanel implements MouseListener
{
	final BlockingQueue<GameEvent> blockingQueue;
	
	private static final long serialVersionUID = 1L;
	private Block[][] block;
	private int height;
	private int width;
	private int SIZE = MyDefaults.PIX_PER_BLOCK;

	GamePanel(final BlockingQueue<GameEvent> bq)
	{
		addMouseListener(this);
		setLayout(null);
		blockingQueue = bq;
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int posX = 0;
		int posY = 0;
		for (int i=1; i<=width; ++i)
		{
			for (int j=1; j<=height; ++j)
			{
				if (block[i][j] != null)
				{
					Rectangle2D rectangle = new Rectangle2D.Double(posX, posY, SIZE, SIZE);
					if (block[i][j].isMarked())
					{
						g2d.setColor(getBackground());
					}
					else
					{
						g2d.setColor(new Color(block[i][j].getColorR(), block[i][j].getColorG(), block[i][j].getColorB()));
					}
					g2d.fill(rectangle);
	                g2d.setColor(Color.BLACK);
	                g2d.draw(rectangle);
	                posY = posY + SIZE;
				}
			}
			posX = posX + SIZE;
			posY = 0;
		}
	}
	
	/**przekazuje informacje do wyrysowania,
	 * wywoluje repaint()
	 * (by nie wywo³ywaæ paintComponent bezpoœrednio)
	*/
	void draw(Block[][] board, int width, int height)
	{
		this.block = board;
		this.height = height;
		this.width = width;
		setPreferredSize(new Dimension(width*SIZE, height*SIZE));
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e)
	{
		try
		{				
			blockingQueue.put(new BlockPressedEvent(e.getX()/SIZE+1, e.getY()/SIZE+1));
		}
		catch(Exception ex)			
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}			
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		try
		{				
			blockingQueue.put(new BlockReleasedEvent(e.getX()/SIZE+1, e.getY()/SIZE+1));
		}
		catch(Exception ex)			
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}	
	}
}
