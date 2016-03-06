package pl.kstachyr.blocksgame.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * lista klocków w jednej pionowej linii
 * pozwala ³atwo usunaæ klocki przeznaczone do usuniêcia
 */
public class Column
{
	/**lista klocków w pionowej linii*/
	private final LinkedList<Block> blocks = new LinkedList<Block>();
	
	/**
	 * wstawia element do listy
	 * 
	 * @param element wstawiany element
	 */
	void push(final Block element)
	{
		blocks.add(element);
	}

	/**
	 * wyjmuje element z listy
	 */
	Block pop()
	{
		if (!blocks.isEmpty())
		{
		     return blocks.removeFirst();		     
		}
		else
		{
			return new Block(MyColor.BARRIER);
		}
	}
	
	/**
	 * usuwa klocki przeznaczone do usuniêcia
	 */
	void deleteBlocks()
	{
		Iterator<Block> iterator = blocks.iterator();
		while (iterator.hasNext())
		{
		   Block element = iterator.next();
		   if(element.isToDelete())
		   {
			   iterator.remove();
		   }
		}
	}
}