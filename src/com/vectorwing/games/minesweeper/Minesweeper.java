package com.vectorwing.games.minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.vectorwing.games.minesweeper.enums.GameLevel;
import com.vectorwing.games.minesweeper.gui.MainGUI;
import com.vectorwing.games.minesweeper.logic.MainGame;
import com.vectorwing.games.minesweeper.reference.Names;

/**
 * A remake of the classic deduction minigame. Clear the board while tip-toeing around the scattered mines!
 * @author VectorWing
 */
public class Minesweeper extends JFrame {

	private static final long	serialVersionUID = 1L;
	private MainGUI				gui;
	private MainGame			game;
	
	public Minesweeper()
	{
		this.initWindowLayout();
		this.initWindowProperties();
	}
	
	private void initWindowProperties()
	{
		this.setTitle(Names.GAME_TITLE + " " + Names.GAME_VERSION);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	private void initWindowLayout()
	{
		this.gui = new MainGUI();
		this.setContentPane(this.gui);
		
		this.game = new MainGame(gui, GameLevel.EASY);
		
		/*this.gui.getTileGrid().newGame(GameLevel.EASY, this);
		this.gui.getGameInfo().setParameters(GameLevel.EASY);
		this.gui.getGameInfo().setClear();*/
		
		this.game.begin();
		
		this.pack();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Minesweeper main = new Minesweeper();
                main.setVisible(true);
            }
        });

	}

}
