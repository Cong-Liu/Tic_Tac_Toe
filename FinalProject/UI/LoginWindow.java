package FinalProject.UI;

import java.awt.FlowLayout;
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
import javax.swing.JTextField;

import FinalProject.Application;
import FinalProject.GameSystem;

public class LoginWindow extends JFrame {
	private JPanel logInMenu;
	private JTextField userText;
	private JLabel logInMessage;
	private JLabel userLabel;
	private JPanel logInField;
	private JPanel logInButtonField;
	
	private GameSystem gameSystem;
	private MainWindow main;
	
	//draw login window
	public LoginWindow(GameSystem system, MainWindow mainWin) {
		super("TicTacToe Game Login");
		this.setSize(600, 500);
		
		gameSystem = system;
		main = mainWin;
		
		iniateLogIn();
		this.add(logInMenu);
	}
	
	private void iniateLogIn() { 	
		//formatting
		
		logInMenu = new JPanel();
		logInMenu.setSize(200, 450);
		logInMenu.setLayout(new BoxLayout(logInMenu, BoxLayout.Y_AXIS));

		//initialize components 
		logInMessage = new JLabel("Welcome");
		logInField = new JPanel();
		logInButtonField = new JPanel(); 
		
		placeComponents();
		
		//add components
		logInMenu.add(logInMessage);
		logInMessage.setVisible(true);
		logInMenu.add(logInField);
		logInField.setVisible(true);
		logInMenu.add(logInButtonField);
		logInButtonField.setVisible(true);
	}
	
	//show login window
	public void display(){
		this.setVisible(true);
	}
	
	//user clicked login button
	private void login(){
		String logInName = userText.getText().toString();
		try {
			gameSystem.logIn(logInName);
			main.display();
			
			this.setVisible(false);
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//user clicked register button
	private void register(){
		String logInName = userText.getText().toString();
		try {
			gameSystem.addNewUser(logInName);
			JOptionPane.showMessageDialog(null, "You have been successfully registered with username " + logInName + ", please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void placeComponents() {
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 50, 25);
		logInField.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		logInField.add(userText);
		
		logInButtonField.setLayout(new FlowLayout());
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		logInButtonField.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		}); 
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(180, 80, 80, 25);
		logInButtonField.add(registerButton);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
	}
}
