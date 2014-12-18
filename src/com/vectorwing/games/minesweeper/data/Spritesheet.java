package com.vectorwing.games.minesweeper.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Spritesheet {
	
	private Map<String, ImageIcon> sheet;
	
	public Spritesheet()
	{
		sheet = new HashMap<String, ImageIcon>(); 
	}
	
	public ImageIcon getSprite(String name)
	{
		return sheet.get(name);
	}
	
	public void addSprite(BufferedImage img, String name)
	{
		this.sheet.put(name, new ImageIcon(img));
	}
	
	/**
	 * Generates a sprite sheet from a given image, cutting it on equal-sized tiles of 'width' and 'height'.
	 * The string array defines the keys for the objects. If null or lacking enough names, subsequent names will
	 * default to "sprite_index", 'index' being the current sprite count.
	 * This function will erase any and all sprites currently stored within before generation.
	 */
	public void generateSpritesFromImage(BufferedImage img, ArrayList<String> names,
			int width, int height, int sprite_cols, int sprite_rows)
	{
		this.sheet.clear();
		int index = 0;
		
		for (int i = 0; i < sprite_rows; i++)
		{
		    for (int j = 0; j < sprite_cols; j++)
		    {
		    	String key = "";
		    	if (names == null)
		    		key = "sprite_" + String.valueOf(index);
		    	else if (names.get(index) == null)
		    		key = "sprite_" + String.valueOf(index);
		    	else
		    		key = names.get(index);
		    		
		    	ImageIcon icon = new ImageIcon(img.getSubimage(j * width, i * height, width, height));
		    	this.sheet.put(key, icon);
		    	index++;
		    	//img_tile[(i * sprite_cols) + j] = buffer.getSubimage(j * sprite_width, i * sprite_height, sprite_width, sprite_height);
		    }
		}
	}

}
