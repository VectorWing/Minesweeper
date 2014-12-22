package com.vectorwing.games.minesweeper.gui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.vectorwing.games.minesweeper.data.Spritesheet;
import com.vectorwing.games.minesweeper.reference.Filepath;

/**
 * The grid that holds all tiles for the current game.
 * Responsible for referencing all stored tiles and their states, as well as the graphics used within them.
 **/
public class TileGrid extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ArrayList<Tile>>	tile_array;
	private Spritesheet					img_tile;
	private ArrayList<String>			name_tile;
	
	public TileGrid(GameInfo info)
	{
		this.tile_array = new ArrayList<ArrayList<Tile>>();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0.4f, 0.3f, 0.1f));
		this.initGraphics();
	}
	
	@SuppressWarnings("serial")
	private void initGraphics() {
		BufferedImage buffer;
		
		try {
			buffer = ImageIO.read(new File(Filepath.IMG_TILE));
			System.out.println("Resource '" + Filepath.IMG_TILE + "' loaded succesfully.");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to load a resource!");
			buffer = null;
		}
		
		this.name_tile = new ArrayList<String>()
		{{
			add("space_empty");
			add("space_1");
			add("space_2");
			add("space_3");
			add("space_4");
			add("space_mine");
			add("space_5");
			add("space_6");
			add("space_7");
			add("space_8");
			add("tile_normal");
			add("tile_flag");
			add("tile_question");
			add("tile_clicking");
			add("error");
		}};
		
		this.img_tile = new Spritesheet();
		this.img_tile.generateSpritesFromImage(buffer, name_tile, 24, 24, 5, 3);
	}
	
	public void updateTileGraphics(int pos_x, int pos_y, String key)
	{
		this.tile_array.get(pos_y).get(pos_x).setIcon(this.img_tile.getSprite(key));
	}

	public ArrayList<ArrayList<Tile>> getTileArray()
	{
		return this.tile_array;
	}

	public void setTileArray(ArrayList<ArrayList<Tile>> tile_array)
	{
		this.tile_array = tile_array;
	}

	public Spritesheet getSpritesheet()
	{
		return this.img_tile;
	}
}
