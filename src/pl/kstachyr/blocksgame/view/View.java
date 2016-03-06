package pl.kstachyr.blocksgame.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import pl.kstachyr.blocksgame.defaults.MyDefaults;
import pl.kstachyr.blocksgame.event.*;
import pl.kstachyr.blocksgame.model.Block;
import pl.kstachyr.blocksgame.model.ScreenPattern;

/**
 * widok z MVC,
 * implementuje pe³ny interfejs u¿ytkownika,
 * odbiera wzór ekranu i go rysuje,
 * wysy³a do kolejki zdarzeñ decyzje u¿ytkownika na podstawie akcji interfejsu (jako eventy)
 */
public class View
{
	/**kolejka zdarzeñ do komunikacji z kontrolerem*/
	final BlockingQueue<GameEvent> blockingQueue;
	/**klasa reprezentuj¹ca graficzne okno programu*/
	private GameFrame frame;
	 
	/**
	 * konstruktor widoku, tworzy obiekt klasy GameFrame
	 * 
	 * @param bq BlockingQueue dla obs³ugiwanych zdarzeñ
	 * @param screenPattern ScreenPattern - informacje dla widoku, co ma rysowaæ
	 */
	public View(final BlockingQueue<GameEvent> bq, final ScreenPattern screenPattern)
	{
		this.blockingQueue = bq;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				frame = new GameFrame();
				refresh(screenPattern);
			}
		});
	}
	
	/**
	 * odœwie¿a widok (powo³ywany w¹tek swing)
	 */
	public void refresh(final ScreenPattern screenPattern)
	{
		SwingUtilities.invokeLater(new Runnable()
		{		
			@Override
			public void run()
			{
				frame.drawGamePanel(screenPattern.board, screenPattern.boardWidth, screenPattern.boardHeight);
				frame.setGameCode(screenPattern.gameCode);
				frame.setScore(screenPattern.score);
				frame.pack();
			}
		});
	}
	
	/**klasa reprezentuj¹ca ca³e okno gry i pe³ny interfejs*/
	class GameFrame extends JFrame
	{
		private static final long serialVersionUID = 1L;
		private final JLabel scoreLabel;
		private final JLabel seedLabel;
		private final GamePanel gamePanel;
		private final JPanel bottomPanel;
		
		GameFrame()
		{
			super("Blocks Game");
			setLayout(new BorderLayout());
			
			setJMenuBar(createMenu());
			
			gamePanel = new GamePanel(blockingQueue);
			add(gamePanel, BorderLayout.CENTER);
			
			scoreLabel = new JLabel();
			seedLabel = new JLabel();
			bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.add(scoreLabel, BorderLayout.LINE_START);
			bottomPanel.add(seedLabel, BorderLayout.LINE_END);
			add(bottomPanel, BorderLayout.SOUTH);
			
			setLocationByPlatform(true);
			
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setVisible(true);			
		}
		
		public void drawGamePanel(final Block[][] block, final int width, final int height)
		{
			gamePanel.draw(block, width, height);
		}

		public void setGameCode(final String gameCode)
		{
			seedLabel.setText("Game: " + gameCode);		
		}

		public void setScore(final int score)
		{
			scoreLabel.setText("Score: " + Integer.toString(score));		
		}

		private JMenuBar createMenu()
		{
			ActionListener randomGameListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						blockingQueue.put(new NewGameEvent(MyDefaults.BOARD_WIDTH, MyDefaults.BOARD_HEIGHT, MyDefaults.NUMBER_OF_COLORS));
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						throw new RuntimeException(ex);
					}			
				}
			};
			
			ActionListener restartGameListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						blockingQueue.put(new RestartGameEvent());
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						throw new RuntimeException(ex);
					}			
				}
			};

			ActionListener exitListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						blockingQueue.put(new FinishGameEvent());
					}
						catch(Exception ex)
						{
						ex.printStackTrace();
						throw new RuntimeException(ex);
						}	
				}
			};
			
			JMenu newGameMenu = new JMenu("New game");
				
			JMenuItem exitMenu = new JMenuItem("Exit");
			exitMenu.addActionListener(exitListener);
			
			JMenuItem randomGameMenu = new JMenuItem("Standard game");
			randomGameMenu.addActionListener(randomGameListener);
			
			JMenuItem customGameMenu = new JMenuItem("Custom game");
			customGameMenu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					creteOptionsDialog();
				}
			});
			
			JMenuItem restartGameMenu = new JMenuItem("Restart");
			restartGameMenu.addActionListener(restartGameListener);
			
			newGameMenu.add(randomGameMenu);
			newGameMenu.add(customGameMenu);
			newGameMenu.add(restartGameMenu);
			
			JMenu gameMenu = new JMenu("Game");
			gameMenu.add(newGameMenu);
			gameMenu.add(exitMenu);
			JMenuBar menu = new JMenuBar();
			menu.add(gameMenu);
			return menu;
		}

		private JDialog creteOptionsDialog()
		{
			{
				final JDialog customGameDialog = new JDialog(frame, "Custom game ", true);
				customGameDialog.setLayout(new BorderLayout());
				
				final JPanel customCenterPanel = new JPanel();
				customCenterPanel.setLayout(new GridLayout(5, 1, 10, 10));
				
				final JLabel customWidthLabel = new JLabel("width (1-99)");
				final JLabel customHeightLabel = new JLabel("height (1-49)");
				final JLabel customColorsLabel = new JLabel("number of colors (2-8)");
				final JLabel customSeedLabel = new JLabel("seed");
				
				Dimension textDimension = new Dimension(20, 16);
				final JTextComponent customWidthText = new JTextField();
				customWidthText.setPreferredSize(textDimension);
				final JTextComponent customHeightText = new JTextField();
				customHeightText.setPreferredSize(textDimension);
				final JTextComponent customColorsText = new JTextField();
				customColorsText.setPreferredSize(textDimension);
				final JTextComponent customSeedText = new JTextField();
				customSeedText.setPreferredSize(new Dimension(120, 12));

				final JPanel customWidthPanel = new JPanel(new BorderLayout());
				customWidthPanel.add(customWidthLabel, BorderLayout.LINE_START);
				customWidthPanel.add(customWidthText, BorderLayout.LINE_END);
				
				final JPanel customHeightPanel = new JPanel(new BorderLayout());
				customHeightPanel.add(customHeightLabel, BorderLayout.LINE_START);
				customHeightPanel.add(customHeightText, BorderLayout.LINE_END);
				
				final JPanel customColorsPanel = new JPanel(new BorderLayout());
				customColorsPanel.add(customColorsLabel, BorderLayout.LINE_START);
				customColorsPanel.add(customColorsText, BorderLayout.LINE_END);
				
				final JPanel customSeedPanel = new JPanel(new BorderLayout());
				customSeedPanel.add(customSeedLabel, BorderLayout.LINE_START);
				customSeedPanel.add(customSeedText, BorderLayout.LINE_END);
				
				final JPanel customButtonsPanel = new JPanel(new BorderLayout());
				final JButton cancelButton = new JButton("Cancel");
				final JButton randomizeButton = new JButton("Randomize seed");
				final JButton playButton = new JButton("Play!");
				customButtonsPanel.add(cancelButton, BorderLayout.LINE_START);
				customButtonsPanel.add(randomizeButton, BorderLayout.CENTER);
				customButtonsPanel.add(playButton, BorderLayout.LINE_END);
				
				customCenterPanel.add(customWidthPanel);
				customCenterPanel.add(customHeightPanel);
				customCenterPanel.add(customColorsPanel);
				customCenterPanel.add(customSeedPanel);
				
				JPanel marginUpPanel = new JPanel();
				marginUpPanel.setPreferredSize(new Dimension(10, 20));
				JPanel marginBottomPanel = new JPanel();
				marginBottomPanel.setPreferredSize(new Dimension(10, 0));
				JPanel marginLeftPanel = new JPanel();
				marginLeftPanel.setPreferredSize(new Dimension(40, 10));
				JPanel marginRightPanel = new JPanel();
				marginRightPanel.setPreferredSize(new Dimension(40, 10));
				customCenterPanel.add(marginBottomPanel);
				
				customGameDialog.add(marginUpPanel, BorderLayout.PAGE_START);
				customGameDialog.add(marginLeftPanel, BorderLayout.LINE_START);
				customGameDialog.add(marginRightPanel, BorderLayout.LINE_END);
				
				customGameDialog.add(customCenterPanel, BorderLayout.CENTER);
				customGameDialog.add(customButtonsPanel, BorderLayout.PAGE_END);
				
				ActionListener cancelListener = new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						customGameDialog.dispose();						
					}
				};
				cancelButton.addActionListener(cancelListener);
				
				ActionListener randomizeSeedListener = new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						int randomInt = new Random().nextInt();
						randomInt = Math.abs(randomInt);
						customSeedText.setText(Integer.toString(randomInt));
					}
				};
				randomizeButton.addActionListener(randomizeSeedListener);
				
				ActionListener customGameListener = new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						Integer customWidth = null;
						Integer customHeight = null;
						Integer customColors = null;
						Integer customSeed = null;
						try
						{
							customWidth = Integer.parseInt(customWidthText.getText());
							customHeight = Integer.parseInt(customHeightText.getText());
							customColors = Integer.parseInt(customColorsText.getText());
							customSeed = Integer.parseInt(customSeedText.getText());
							
							boolean messageNeeded = false;
							if (customWidth <1 || customWidth>99)
							{
								customWidthText.setText("");
								messageNeeded = true;
							}
							
							if (customHeight <1|| customHeight>49)
							{
								customHeightText.setText("");
								messageNeeded = true;
							}
							
							if (customColors <2 || customColors>8)
							{
								customColorsText.setText("");
								messageNeeded = true;
							}
							
							if (customSeed <0)
							{
								customSeedText.setText("");
								messageNeeded = true;
							}
							
							if(messageNeeded)
							{
								JOptionPane.showMessageDialog(frame, "Insert correct values!","", JOptionPane.PLAIN_MESSAGE);
							}
							else
							{

								blockingQueue.put(new NewGameEvent(customWidth, customHeight, customColors, customSeed));
								customGameDialog.dispose();
							}
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(frame, "Insert correct values!","", JOptionPane.PLAIN_MESSAGE);
							if (customWidth ==  null) customWidthText.setText("");
							if (customHeight ==  null) customHeightText.setText("");
							if (customColors ==  null) customColorsText.setText("");
							if (customSeed ==  null) customSeedText.setText("");
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
							throw new RuntimeException(ex);
						}
					}
				};
				playButton.addActionListener(customGameListener);
							
				customGameDialog.setLocationByPlatform(true);
				customGameDialog.pack();
				customGameDialog.setResizable(false);
				customGameDialog.setVisible(true);
				return customGameDialog;
			}
		}
	}
}