package com.vectorwing.games.minesweeper.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Spritesheet extends HashMap<String, ImageIcon> {
	
	private static final long serialVersionUID = 1L;

	public Spritesheet()
	{
		//
	}
	
	public ImageIcon getSprite(String name)
	{
		return this.get(name);
	}
	
	/** Inserts a new sprite manually, identified by the given name. **/
	public void add(BufferedImage img, String name)
	{
		this.put(name, new ImageIcon(img));
	}
	
	/**
	 * Generates a sprite sheet from a given image, cutting it on equal-sized tiles of 'width' and 'height'.
	 * The string array defines the keys for the objects. If null or lacking enough names, subsequent names will
	 * default to "sprite_index", 'index' being the current sprite count.
	 * This function will erase any and all sprites currently stored within before generation.
	 */
	public void generateSpritesFromImage(BufferedImage img, ArrayList<String> names,
			int sprite_width, int sprite_height, int sprite_cols, int sprite_rows)
	{
		this.clear();
		int index = 0;
		
		for (int i = 0; i < sprite_rows; i++)
		{
		    for (int j = 0; j < sprite_cols; j++)
		    {
		    	String key = "";
		    	
		    	if (!names.isEmpty())
		    		key = names.remove(0);
		    	else
		    		key = "sprite_" + String.valueOf(index);
		    		
		    	ImageIcon icon = new ImageIcon(img.getSubimage(j * sprite_width, i * sprite_height, sprite_width, sprite_height));
		    	this.put(key, icon);
		    	index++;
		    }
		}
	}

}
