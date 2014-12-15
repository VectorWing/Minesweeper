package com.vectorwing.games.minesweeper.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establishes a database connection.
 */
public class ConnectionFactory {

	public static Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/highscore", "root", "");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erro SQLException ao abrir conexão em ConnectionFactory", ex);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Erro ao abrir conexão com o banco de dados", ex);
		}
			
	}
}