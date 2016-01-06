package FinalProject;

public class ChessBoard {
	private int[] chessboard;
	
	public ChessBoard(){
		chessboard = new int[9];
	}
	
	//return false means this move is not valid
	public boolean setMove(int move, int player){
		if(chessboard[move]!=0) return false;
		chessboard[move] = player;
		return true;
	}
	
	public boolean validMove(int move){
		if(chessboard[move]!=0) return false;
		else return true;
	}
	
	public int getPosition(int pos) throws Exception{
		if(pos<0||pos>8) throw new Exception("Board index is out of range.");
		return chessboard[pos];
	}
	
	public int getPosition(int row, int col){
		int idx = row*3 + col;
		return chessboard[idx];
	}
	
	//return 0 means game is not over yet
	//return 1 means someone wins
	//return -1 means tie game
	public int checkWin(){
		//check winning combos through the center
		if(chessboard[4] != 0){
			//check vertical column through the center
			if(chessboard[1] == chessboard[4] && chessboard[4] == chessboard[7]){
				return 1;
			}
			//check horizontal row through the center
			else if(chessboard[3] == chessboard[4] && chessboard[4] == chessboard[5]){
				return 1;
			}
					
			//check diagonal from top left corner
			else if(chessboard[0] == chessboard[4] && chessboard[4] == chessboard[8]){
				return 1;
			}
						
			//check diagonal from top right corner
			else if(chessboard[2] == chessboard[4] && chessboard[4] == chessboard[6]){
				return 1;
			}
		}
					
		//check remaining winning combos through top left corner
		if(chessboard[0] != 0){
			//check horizontal row 
			if(chessboard[0] == chessboard[1] && chessboard[1] == chessboard[2]){
				return 1;
			}
			//check vertical column 
			else if(chessboard[0] == chessboard[3] && chessboard[3] == chessboard[6]){
				return 1;
			}
		}
					
		//check remaining winning combos through the bottom right corner
		if(chessboard[8] != 0){
			//check vertical column
			if(chessboard[2] == chessboard[5] && chessboard[5] == chessboard[8]){
				return 1;
			}
			//check horizontal row
			else if(chessboard[6] == chessboard[7] && chessboard[7] == chessboard[8]){
				return 1;
			}
		}
			
		//check if board has empty position
		for(int i=0;i<9;i++){
			if(chessboard[i]==0) return 0;
		}
			
		//tie game
		return -1;
	}
}
