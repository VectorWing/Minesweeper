package com.vectorwing.games.minesweeper;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Minesweeper()
	{
		// TODO: Initialize basic window properties
		this.initWindowProperties();
		
		// TODO: Initialize layout for the main panel
		this.initWindowLayout();
		
		// TODO: Start an EASY Minesweeper game by default when open
	}
	
	private void initWindowProperties()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initWindowLayout()
	{
		
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
