package FinalProject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class GameSystem {
	private SearchUserList userDataBase;
	private User currentUser;

	public GameSystem() throws Exception {
		userDataBase = new SearchUserList();
		userDataBase = loadUsers("UserData.txt");
		currentUser = new User();
	}
/*
 * ADDED
 */
	
	public GameSystem(String fileName) throws Exception{
		userDataBase = new SearchUserList();
		userDataBase = loadUsers(fileName);
	}
	
	
	public GameSystem(SearchUserList user, User auser) {
		userDataBase = user;
		currentUser = auser;
	}

	public SearchUserList loadUsers(String fileName) throws Exception {
		Scanner readFile;
		try {
			readFile = new Scanner(new FileReader(fileName));
			while (readFile.hasNext()) {
				User u = new User(readFile.nextLine());
				userDataBase.add(u);
			}
		} catch (FileNotFoundException e) {
			throw new Exception ("File is not found. ");

		}readFile.close();
		return userDataBase;
	}

	public void writeFile(String fileName) throws Exception{

		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(userDataBase.toString());
			System.out.println(userDataBase.toString());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			throw new Exception ("The Character Encoding is not supported. ");
		}

	}

	public SearchUserList getUserData() {
		return userDataBase;
	}

	public boolean isExisting(String name) {
		boolean exist = false;
		for (int i = 0; i < userDataBase.getIndex(); i++) {
			if (userDataBase.getElement(i).getUserName().equals(name)) {
				exist = true;
			}
		}
		return exist;
	}

    public void addNewUser(String name) throws Exception {
		if (name.length() < 3)
			throw new Exception("UserID is not valid");
		else if(searchName(name)!= -1)
			throw new Exception ("This userID is existed.");
		
		User currentUser = new User(); 
		currentUser.setUserName(name);
		userDataBase.add(currentUser);
	}
    
	public void logIn(String aStr) throws Exception {
		int pos = searchName(aStr);
		if (pos == -1)
			throw new Exception("Your UserID is not existed. ");
		
		currentUser = userDataBase.getElement(pos);
	}

	public void logOut() {
		currentUser = null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void sortRate() throws Exception {
		
		for (int i = 0; i < userDataBase.getIndex(); i++) {
			int maxrate = i;
			for (int j = i+1; j < userDataBase.getIndex(); j++) {
				int a = userDataBase.getElement(maxrate).compareTo(
						userDataBase.getElement(j));
				if (a < 0) {
					maxrate = j;
				}
			}
			userDataBase.swap(i, maxrate);
		}
	}

	public void sortWins() throws Exception {
		for (int i = 0; i < userDataBase.getIndex(); i++) {
			int maxwin = i;
			for (int j = i+1; j < userDataBase.getIndex(); j++) {
				if (userDataBase.getElement(maxwin).getWins() < userDataBase
						.getElement(j).getWins()) {
					maxwin = j;
				}
			}
			userDataBase.swap(i, maxwin);
		}
	}

	public String dispSB() {
		// it will display ranking list on score board
		String aStr = "";
		for (int i = 0; i < userDataBase.getIndex(); i++) {
			aStr += "No." + i + "\t" + userDataBase.getElement(i).display() + "\n";
		}
		return aStr;
		
	}
	

	public void reset() {
		userDataBase.reset();
	}

	public void deletID() {
		// user can delete his account from UserDataBase
		userDataBase.delete(currentUser);
	}
	
	public int searchName(String a){
		for(int i = 0; i<userDataBase.getIndex();i++){
			if(userDataBase.getElement(i).getUserName().equals(a))
				return i;
		}
		return -1;
	}

}
