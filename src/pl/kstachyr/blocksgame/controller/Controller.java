package pl.kstachyr.blocksgame.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.kstachyr.blocksgame.event.*;
import pl.kstachyr.blocksgame.model.Model;
import pl.kstachyr.blocksgame.view.View;

/**
 * kontroler z MVC,
 * zawiera g³ówn¹ pêtle programu,
 * poœredniczy miêdzy widokiem a modelem,
 * wywo³uje metody modelu,
 * wysy³a makiety do widoku
 */
public class Controller
{
	/**widok MVC*/
	private final View view;	
	/**model MVC*/
	private final Model model;
	/**kolejka zdarzeñ z widoku*/
	private final BlockingQueue<GameEvent> blockingQueue;
	/**mapa zachowañ programu na poszczeglne eventy (GameEvent -> EventStrategy)*/
	private final Map<Class<? extends GameEvent>, EventStrategy> eventStrategyMap;
	
	/**
	 * kostruktor kontrolera
	 * 
	 * @param view widok
	 * @param model model
	 * @param blockingQueue kolejka zdarzeñ do komunikacji z z widokiem
	 */
	public Controller(final View view, final Model model, final BlockingQueue<GameEvent> blockingQueue)
	{
		this.view = view;
		this.model = model;
		this.blockingQueue = blockingQueue;
		eventStrategyMap = new HashMap<Class<? extends GameEvent>, EventStrategy>();
		fillEventStategyMap();
	}
	
	/**
	 * klasa abstrakcyjna okreœlaj¹ca strategiê dzia³ania na okreœlone eventy,
	 * wymaga zdefiniowania odpowiedniej metody runStrategy dla ka¿dej ze strategii
	 */
	private abstract class EventStrategy
	{
		abstract public void runStrategy(final GameEvent event);
	}
	
	/**
	 * strategia dla eventu NewGameEvent
	 * tworzy now¹ grê
	 */
	private class NewGameStrategy extends EventStrategy
	{
		public void runStrategy(final GameEvent event)
		{
			NewGameEvent newGameEvent = (NewGameEvent) event;
			model.newGame(newGameEvent.getWidth(), newGameEvent.getHeight(), newGameEvent.getColors(), newGameEvent.getSeed());
		}
	}
	
	/**
	 * strategia dla eventu FinishGameStrategy
	 * wy³¹cza program
	 */
	private class FinishGameStrategy extends EventStrategy
	{
		public void runStrategy(final GameEvent event)
		{
			System.exit(0);
		}
	}
	
	/**
	 * strategia dla eventu BlockPressedEvent
	 * zaznacza grupê klocków
	 */
	private class BlockPressedStrategy extends EventStrategy
	{
		public void runStrategy(final GameEvent event)
		{
			try
			{
				BlockPressedEvent blockPressedEvent = (BlockPressedEvent) event;
				model.markGroupOfBlocks(blockPressedEvent.getColumnNumber(), blockPressedEvent.getRowNumber());
			}
			catch (NullPointerException e)
			{
				model.unmarkAllBlocks();
			}
		}
	}
	
	/**
	 * strategia dla eventu BlockReleasedEvent
	 * usuwa zaznacz¹n¹ grupê klocków, jeœli puszczona na zaznacznym,
	 * lub odznacza zaznaczone klocki, jeœli puszczono na niezaznaczonym,
	 * po usuniêciu sprawdza, czy plansza jest wyczyszczona
	 */
	private class BlockReleasedStrategy extends EventStrategy
	{
		public void runStrategy(final GameEvent event)
		{
			try
			{
				BlockReleasedEvent blockReleasedEvent = (BlockReleasedEvent) event;
				model.unmarkOrDelete(blockReleasedEvent.getColumnNumber(), blockReleasedEvent.getRowNumber());
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				model.unmarkAllBlocks();
			}
		}
	}
	
	/**
	 * strategia dla eventu RestartGameEvent
	 * restartuje grê
	 */
	private class RestartGameStrategy extends EventStrategy
	{
		public void runStrategy(final GameEvent event)
		{
			model.restartGame();
		}
	}
	
	/**
	 * uzupe³nianie mapy strategii dla odpowiednich eventów
	 */
	private void fillEventStategyMap()
	{
		eventStrategyMap.put(NewGameEvent.class, new NewGameStrategy());
		eventStrategyMap.put(FinishGameEvent.class, new FinishGameStrategy());
		eventStrategyMap.put(BlockPressedEvent.class, new BlockPressedStrategy());
		eventStrategyMap.put(BlockReleasedEvent.class, new BlockReleasedStrategy());
		eventStrategyMap.put(RestartGameEvent.class, new RestartGameStrategy());
	}
	
	/**
	 * nieskoñczona pêtla,
	 * pobiera zdarzenia z kolejki i wywo³uje odpowiedni¹ strategiê dla tego zdarzenia z mapy,
	 * w¹tek g³ówny programu
	 */
	public void work()
	{
		while(true)
		{
			try
			{
				GameEvent event = blockingQueue.take();
				EventStrategy eventStrategy = eventStrategyMap.get(event.getClass());
				eventStrategy.runStrategy(event);
				view.refresh(model.getScreenPattern());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}