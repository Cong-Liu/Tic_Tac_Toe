package FinalProject;

public class AI2 extends Player {
	private ChessBoard board;

	public AI2(ChessBoard cheeseBoard) {
		board = cheeseBoard;
	}

	public AI2(String str, int a) throws Exception {
		super(str, a);

	}
	
	public boolean areEqual(int row1, int col1, int row2, int col2){
		if(row1<0||col1<0||row2<0||col2<0)
			return false;
		else if(row1>2||col1>2||row2>2||col2>2)
			return false;
		else if(board.getPosition(row1, col1) * board.getPosition(row2, col2) == 0)
			return false;
		return board.getPosition(row1, col1) == board.getPosition(row2, col2);
	}

	public int getMove() throws Exception {
		int pos = -1;
		for(int row = 0;row <3; row++){
			for(int col = 0; col<3;col++){
				if(board.getPosition(row, col) == 0){
					pos = row*3+col;
					// check west
					if(areEqual(row,col-1,row,col-2)) return pos;
					//check east
					else if(areEqual(row,col+1,row,col+2)) return pos;
					//check north
					else if(areEqual(row-1,col,row-2,col)) return pos;
					//check south
					else if(areEqual(row+1,col,row+2,col)) return pos;
					//check NW
					else if(areEqual(row-1,col-1,row-2,col-2)) return pos;
					//check NE
					else if(areEqual(row-1, col+1, row-2,col+2)) return pos;
					//check SW
					else if(areEqual(row+1,col-1, row+2,col-2)) return pos;
					//check SE
					else if(areEqual(row+1,col+1,row+2,col+2)) return pos;
				}
			}
		}
		if(pos!=-1) return pos;
		throw new Exception("AI unable to move. No empty positions.");
	}
}
