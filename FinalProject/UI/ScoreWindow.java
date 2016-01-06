package FinalProject.UI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import FinalProject.Application;
import FinalProject.GameSystem;
import FinalProject.SearchUserList;


public class ScoreWindow extends JFrame {
	private JPanel scoreboard;
	private JScrollPane panel;
	private JTable table;
	private String[] columnNames = new String[] { "User Name", "# Total Games", "# Win Games" };
	private JPanel navButtons;
	private JButton btnSortWinTtl;
	private JButton btnSortPcntWin;
	
	private GameSystem gameSystem;
	
	//draw score window
	public ScoreWindow(GameSystem system){
		super("Score Board");
		this.setSize(600, 500);
		scoreboard = new JPanel();
		panel = new JScrollPane();
		
		gameSystem = system;
		
		scoreboard.setLayout(new GridLayout(2,1));
		createNavButtons();
		scoreboard.add(navButtons);
		scoreboard.add(panel);
		this.add(scoreboard);
	}
	
	//draw table with current user list and then show score board
	public void display() {
		try
		{
			gameSystem.sortRate();
			drawTable();
			this.setVisible(true);
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//re-draw table with user list
	private void drawTable(){
		if(table!=null){
			table.setVisible(false);
			panel.remove(table);
		}
		
		SearchUserList list = gameSystem.getUserData();
		
		Object[][] temp = new Object[list.getIndex()][3];
		for(int i = 0;i<list.getIndex(); i++){
			temp[i] = list.getElement(i).toString().split(",");
		}
		
		table = new JTable(temp, columnNames);
		panel.setViewportView(table);
	}
	
	
	private void createNavButtons(){
		navButtons =  new JPanel();
		navButtons.setLayout(new FlowLayout());
		
		
		btnSortWinTtl = new JButton("Sort by win Totals");
		btnSortWinTtl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//user clicked sort by win button
				try {
					gameSystem.sortWins();
					drawTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSortPcntWin = new JButton("Sort by win percent");
		btnSortPcntWin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//user clicked sort by rate button
				try {
					gameSystem.sortRate();
					drawTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		navButtons.add(btnSortWinTtl);
		navButtons.add(btnSortPcntWin);
		navButtons.setVisible(true);
	}
}
