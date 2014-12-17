package com.vectorwing.games.minesweeper.enums;

public enum TileContent {
	
	MINE(-1),
	HINT0(0),
	HINT1(1),
	HINT2(2),
	HINT3(3),
	HINT4(4),
	HINT5(5),
	HINT6(6),
	HINT7(7),
	HINT8(8);
	
	private final int id;
	private static TileContent[] values = values();
	
	TileContent(int id)
	{
		this.id = id;
	}

	public int getValue()
	{
		return id;
	}
	
	public TileContent next()
	{
		return values[(this.ordinal()+1) % values.length];
	}
	
}
