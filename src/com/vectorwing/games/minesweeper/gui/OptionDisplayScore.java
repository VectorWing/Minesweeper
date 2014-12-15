package com.vectorwing.games.minesweeper.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.vectorwing.games.minesweeper.data.Score;
import com.vectorwing.games.minesweeper.enums.GameLevel;

public class OptionDisplayScore extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table_score;
	private JLabel lbl_highscore;
	private Vector<String> column_names;
	
	public OptionDisplayScore(ResultSet score, GameLevel diff) throws SQLException
	{
		column_names = new Vector<String>();
		column_names.add("Jogador");
		column_names.add("Tempo");
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.anchor = GridBagConstraints.CENTER;
		
		switch (diff)
		{
		case EASY:
			lbl_highscore = new JLabel("Placar - Fácil");
			break;
		case HARD:
			lbl_highscore = new JLabel("Placar - Médio");
			break;
		case MEDIUM:
			lbl_highscore = new JLabel("Placar - Difícil");
			break;
		default:
			lbl_highscore = new JLabel();
			break;
		}
		gbc.gridy = 0;
		this.add(lbl_highscore, gbc);
		
		ScoreTableModel model = new ScoreTableModel();
		
        Score first_score = new Score(score.getString("player"), score.getInt("score"));
        model.addRow(first_score);
		
		while(score.next())
        {  
			String player = score.getString("player");
			int time = score.getInt("score");
			
            Score new_score = new Score(player, time);
            model.addRow(new_score);
        }
		
		table_score = new JTable(model);
		JScrollPane sp = new JScrollPane(table_score);
		gbc.gridy = 1;
		this.add(sp, gbc);
	}
	
	class ScoreTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private List<String> columnNames = new ArrayList<String>();
	    private List<List> data = new ArrayList<List>();
	    
	    {
	    	columnNames.add("Jogador");
	    	columnNames.add("Tempo");
	    }
	    
	    public void addRow(Score score)
	    {
	    	Vector<String> row = new Vector<String>();
	    	row.add(score.getPlayer());
	        row.add(String.valueOf(score.getTime()));
	        data.add(row);
	        fireTableRowsInserted(data.size() - 1, data.size() - 1);
	    }
	    
	    public boolean isCellEditable(int row, int col)
	    {
	        return false;
	    }

	    public int getColumnCount() {
	        return columnNames.size();
	    }

	    public int getRowCount() {
	        return data.size();
	    }

	    public String getColumnName(int col) {
	    	try
	        {
	            return columnNames.get(col);
	        }
	        catch(Exception e)
	        {
	            return null;
	        }
	    }

	    public Object getValueAt(int row, int col) {
	    	return data.get(row).get(col);
	    }
	}
	
}
