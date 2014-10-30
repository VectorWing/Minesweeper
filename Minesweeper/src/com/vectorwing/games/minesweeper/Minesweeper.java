package com.vectorwing.games.minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel main;
	
	public Minesweeper()
	{
		this.initWindowProperties();
		this.initWindowLayout();
	}
	
	private void initWindowProperties()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void initWindowLayout()
	{
		this.main = new MainWindow();
		this.setContentPane(this.main);
		
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
