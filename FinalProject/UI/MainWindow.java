package FinalProject.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FinalProject.Application;
import FinalProject.Game;
import FinalProject.GameSystem;

public class MainWindow extends JFrame {
	private JLabel header;
	private JPanel menu;
	private JButton[] menuOptions = new JButton[4];
	private WindowAdapter appClose;
	private WindowAdapter windowClose;
	
	private GameSystem gameSystem;
	private LoginWindow login;
	private BoardWindow board;
	private ScoreWindow score;
	
	//draw main window
	public MainWindow(GameSystem system){
		super("TicTacToe Game");
		this.setSize(600, 500);
		
		gameSystem = system;
		MainWindow main = this;
		
		//create window closing adapters
		appClose = new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				try {
					windowEvent.getComponent().setVisible(false);
					gameSystem.writeFile("UserData.txt");
					System.exit(0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		
		windowClose = new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				try {
					windowEvent.getComponent().setVisible(false);
					main.setVisible(true);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		
		//create other windows
		login = new LoginWindow(gameSystem, this);
		login.addWindowListener(appClose);
		board = new BoardWindow(gameSystem);
		board.addWindowListener(windowClose);
		score = new ScoreWindow(gameSystem);
		score.addWindowListener(windowClose);
		
		//when user close main window, exit application
		this.addWindowListener(appClose);
				
		menu = new JPanel();
		header = new JLabel("Welcome", JLabel.CENTER);
		
		initMenu();
		
		logout();	//show login window
	}
	
	//show main window
	public void display(){
		header.setText("Welcome "+ gameSystem.getCurrentUser().getUserName());
		this.setVisible(true);
	}
	
	private void initMenu(){
		//myLogIn.setVisible(false);
		menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));
		menu.add(header);
		
		menuOptions[0] = new JButton("Play Easy Mode");
		menuOptions[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playEasy();
			}
		});
		
		menuOptions[1] = new JButton("Play Difficult Mode");
		menuOptions[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playHard();
			}
		});
		
		menuOptions[2] = new JButton("Scoreboard");
		menuOptions[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showScoreBoard();
			}
		});
		
		//log in button
		menuOptions[3] = new JButton("Log out");
		menuOptions[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		
		//add 3 buttons
		for(int i = 0; i < menuOptions.length; i++){
			menu.add(menuOptions[i]);
		}

		//refreshes JFrame
		this.add(menu);
	}
	
	//play easy game
	private void playEasy(){
		Game game = new Game();
		board.setGame(game);
		board.display();
		this.setVisible(false);
	}
	
	//play difficult game
	private void playHard(){
		Game game = new Game(true);
		board.setGame(game);
		board.display();
		this.setVisible(false);
	}
	
	//show score board
	private void showScoreBoard(){
		score.display();
		this.setVisible(false);
	}
	
	private void logout(){
		gameSystem.logOut();
		this.setVisible(false);
		login.display();
	}
	
	//pop up an error box, useful for exception 
	public void showError1(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
