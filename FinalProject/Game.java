package FinalProject;

public class Game {
	private Player player1;
	private Player player2;
	public ChessBoard board;
	
	//simple AI
	public Game(){
		board = new ChessBoard();
		player1 = new Player();
		player2 = new AI(board);
	}
	
	//difficult AI
	public Game(boolean difficult){
		
		board = new ChessBoard();
		player1 = new Player();
		player2 = new AI2(board);
		
	}

	//human player move
	public int setMove(int move) {
		player1.setMove(move);
		board.setMove(move, 1);
		return board.checkWin();
	}
	
	//AI player move
	public int getMove() throws Exception{
		int move = player2.getMove();
		board.setMove(move, 2);
		return move;
	}
	
	//check if game over or not. Return true means game over
	public int checkGameStatus(){
		return board.checkWin();
	}
}
