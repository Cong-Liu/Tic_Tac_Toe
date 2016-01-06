package FinalProject;

public class AI extends Player {
	private final static String NAME = "Computer";
	ChessBoard board;
	
	public AI(ChessBoard chessBoard) {
		super();
		board = chessBoard;
	}

	public AI(int a) throws Exception {
		super(NAME, a);
	}
	
	public int getMove() throws Exception {
		int a;
		
		do{
			a = (int)(Math.random()*9);
		} while(!board.validMove(a));
		
		super.setMove(a);
		return a;
	}
}
