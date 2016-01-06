package FinalProject;

import javax.swing.JOptionPane;

import FinalProject.UI.BoardWindow;
import FinalProject.UI.LoginWindow;
import FinalProject.UI.MainWindow;
import FinalProject.UI.ScoreWindow;

public class Application {

	//this is the start point of application
	public static void main(String[] args) {
		try
		{
			//initialize game system. One application only have one global game system instance
			GameSystem system = new GameSystem("UserData.txt");
			
			//initialize main windows
			MainWindow main = new MainWindow(system);
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
