package com.vectorwing.games.minesweeper.data;

import java.sql.ResultSet;

/**
 * Interface for classes who manage data with Score objects.
 */
public interface ScoreDAO {
	
	public void inserirScoreEasy(Score score);
	public void inserirScoreMedium(Score score);
	public void inserirScoreHard(Score score);

	public ResultSet listarScoreEasy();
	public ResultSet listarScoreMedium();
	public ResultSet listarScoreHard();
	
}
