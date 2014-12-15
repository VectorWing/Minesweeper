package com.vectorwing.games.minesweeper.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScoreDAO for MySQL.
 */
public class MYSQLScoreDAO implements ScoreDAO {

	
	Connection conn;
	public MYSQLScoreDAO(){
		conn = ConnectionFactory.getConnection();
	}
	
	public void inserirScoreEasy(Score score) {
		try {
			String inserirScoreSQL = "INSERT INTO score_easy (player, score) VALUES"
					+ "(?,?)";
			
			PreparedStatement ps = conn.prepareStatement(inserirScoreSQL);
			ps.setString(1, score.getPlayer());
			ps.setInt(2, score.getTime());
				
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@Override
	public void inserirScoreMedium(Score score) {
		try {
			String inserirScoreSQL = "INSERT INTO score_medium (player, score) VALUES"
					+ "(?,?)";
			
			PreparedStatement ps = conn.prepareStatement(inserirScoreSQL);
			ps.setString(1, score.getPlayer());
			ps.setInt(2, score.getTime());
				
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void inserirScoreHard(Score score) {
		try {
			String inserirScoreSQL = "INSERT INTO score_hard (player, score) VALUES"
					+ "(?,?)";
			
			PreparedStatement ps = conn.prepareStatement(inserirScoreSQL);
			ps.setString(1, score.getPlayer());
			ps.setInt(2, score.getTime());
				
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet listarScoreEasy() {
		try {
			String listScoreEasySQL1 = "SELECT * FROM score_easy ORDER BY score ASC";
			
			PreparedStatement psL = conn.prepareStatement(listScoreEasySQL1);
			
			ResultSet rst = psL.executeQuery(listScoreEasySQL1);
			
			List<Score> listarScoreEasy = new ArrayList<Score>();
			while (rst.next()) {
			    Score score = new Score();
			    score.setPlayer(rst.getString("player"));
			    score.setTime(rst.getInt("score"));
			    listarScoreEasy.add(score);
			}
			//return listarScoreEasy;
			return rst;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Erro ao listar Score",e);
		}
	}

	@Override
	public ResultSet listarScoreMedium() {
		try {
			String listScoreEasySQL1 = "SELECT * FROM score_medium ORDER BY score ASC";
			
			PreparedStatement psL = conn.prepareStatement(listScoreEasySQL1);
			
			ResultSet rst = psL.executeQuery(listScoreEasySQL1);
			List<Score> listarScoreEasy = new ArrayList<Score>();
			while (rst.next()) {
			    Score score = new Score();
			    score.setPlayer(rst.getString("player"));
			    score.setTime(rst.getInt("score"));
			    listarScoreEasy.add(score);
			}
			//return listarScoreEasy;
			return rst;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Erro ao listar Score",e);
		}
	}


	@Override
	public ResultSet listarScoreHard() {
		try {
			String listScoreEasySQL1 = "SELECT * FROM score_hard ORDER BY score ASC";
			
			PreparedStatement psL = conn.prepareStatement(listScoreEasySQL1);
			
			ResultSet rst = psL.executeQuery(listScoreEasySQL1);
			List<Score> listarScoreEasy = new ArrayList<Score>();
			while (rst.next()) {
			    Score score = new Score();
			    score.setPlayer(rst.getString("player"));
			    score.setTime(rst.getInt("score"));
			    listarScoreEasy.add(score);
			}
			//return listarScoreEasy;
			return rst;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao listar Score",e);
		}
	}




	


}
