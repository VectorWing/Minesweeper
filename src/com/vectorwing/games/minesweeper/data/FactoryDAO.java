package com.vectorwing.games.minesweeper.data;

public class FactoryDAO {

		public static ScoreDAO createScoreDAO(){
			return new MYSQLScoreDAO();
			
		}

}
