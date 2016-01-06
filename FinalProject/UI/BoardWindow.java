package FinalProject.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FinalProject.Application;
import FinalProject.Game;
import FinalProject.GameSystem;
import FinalProject.User;

public class BoardWindow extends JFrame {
	private JLabel header;
	private JPanel board;
	private JButton[] boardPositions = new JButton[9];
	private Game game;
	private int gameStatus;	//0: not win, 1: human win, 2: AI win, -1: tie
	
	private GameSystem gameSystem;
	
	public BoardWindow(GameSystem system) {
		//construct a Window with UI elements
		super("TicTacToe Game");
		this.setSize(500, 500);
		this.setLayout(new GridLayout(4,5));
		
		gameSystem = system;
		
		board = new JPanel();
		header = new JLabel("Welcome!", JLabel.CENTER);
		
		this.add(header);
		this.add(board);
		initBoard();
	}
	
	//show this window and clear all board positions
	public void display(){
		header.setText("Welcome " + gameSystem.getCurrentUser().getUserName());
		for(int i=0; i < boardPositions.length; i++){
			boardPositions[i].setText("");
			boardPositions[i].setEnabled(true);
		}
		this.setVisible(true);
	}
	
	//set the game to board window
	public void setGame(Game aGame){
		game = aGame;
	}
	
	//human click a button in chess board to move
	private String move(int idx){
		try
		{
			//set human move
			game.setMove(idx);
			gameStatus = game.checkGameStatus();
			if(gameStatus!=0) return "X";	//game over, no need ai move
			
			//get ai move
			int move = game.getMove();
			setBoard(move, "O");
			gameStatus = game.checkGameStatus();
			if(gameStatus==1) gameStatus=2;	//2 means ai wins 
		
			return "X";
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	//check game status and do things accordingly
	private void updateStatus(){
		if(gameStatus==0) return;
		
		User currentUser = gameSystem.getCurrentUser();
		if(gameStatus==1){
			//human win
			currentUser.setWins(currentUser.getWins() + 1);
			JOptionPane.showMessageDialog(null, "Congratulations! You win.", "Win", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(gameStatus==2){
			JOptionPane.showMessageDialog(null, "Sorry, you lose.", "Lose", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(gameStatus==-1){
			JOptionPane.showMessageDialog(null, "Game over. No win no lose.", "Tie", JOptionPane.INFORMATION_MESSAGE);
		}
		
		currentUser.setTotal(currentUser.getTotal()+1);

		//disable all buttons
		for(int i=0;i<boardPositions.length;i++){
			boardPositions[i].setEnabled(false);
		}
	}
	
	//draw board grid and 9 buttons
	private void initBoard() {
		board.setLayout(new GridLayout(3,3));
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//board button clicked
				JButton thisButton = (JButton)(e.getSource());
				
				//get chessboard index
				int idx = Integer.parseInt(thisButton.getName());
				
				//pass the move to application class
				String result = move(idx);
				if(result==null) return;
				thisButton.setText(result);
				thisButton.setEnabled(false);
				updateStatus();
			}
		};
		
		//add buttons to JPannel board and action listeners for each button
		for(int i = 0; i < 9; i++) {
			JButton button = new JButton("");
			
			button.setSize(50, 50);
			button.setName(i + "");
			
			button.addActionListener(listener);
			board.add(button);
			boardPositions[i] = button;
		}
	}
	
	//set the board with specified move, useful for AI move
	private void setBoard(int idx, String value){
		if(idx < 0 || idx >= 9) return;
		
		JButton button = boardPositions[idx];
		if(!button.isEnabled()) return;
		
		button.setText(value);
		button.setEnabled(false);
	}
}
