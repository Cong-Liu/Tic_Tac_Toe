package FinalProject;

public class Player extends User{
	private int move;
	public Player(){
		super();
		move = 0;
	}
	
	public Player(String str,  int a) throws Exception{
		super(str);
		move = a;
	}
	
	public void setMove(int a){
		move = a;
	}
	
	public String getPlayer(){
		return super.getUserName();
	}
	public int getMove() throws Exception {
		return move;
	}
}
