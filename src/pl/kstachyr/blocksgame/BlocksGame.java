package pl.kstachyr.blocksgame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import pl.kstachyr.blocksgame.controller.Controller;
import pl.kstachyr.blocksgame.event.GameEvent;
import pl.kstachyr.blocksgame.model.Model;
import pl.kstachyr.blocksgame.view.View;

/**
 * startowa klasa programu
 * tworzy Model, View, Controller i BlockingQueue
 * uruchamia Controller
 */
public class BlocksGame
{
	public static void main(String[] args)
	{
		try
		{
			final Model model = new Model();
			final BlockingQueue<GameEvent> blockingQueue  = new LinkedBlockingQueue<GameEvent>();
			final View view = new View(blockingQueue, model.getScreenPattern());	
			final Controller controller = new Controller(view, model, blockingQueue);
			controller.work();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
