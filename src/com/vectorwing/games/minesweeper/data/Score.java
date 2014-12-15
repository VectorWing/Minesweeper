package com.vectorwing.games.minesweeper.data;

/**
 * The name and victory time of a player.
 */
public class Score {

	private String player;
	private int time;
	
	public Score()
	{
		player = "";
		time = 0;
	}
	
	public Score(String player, int time)
	{
		this.player = player;
		this.time = time;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}
