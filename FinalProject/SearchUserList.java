package FinalProject;


public class SearchUserList extends MyArrayList<User>{
	public SearchUserList(){
		super();
	}
	public SearchUserList(int size) {
		super(size);
	}
	
	public int sequentialSearchName(String name) {
		for(int i = 0; i < getIndex(); i++){
			if(getElement(i).getUserName().compareTo(name) == 0){
				return i;
			}
		}
		return -1;
	}
	
	public int binarySearch(User userKey) {
		boolean found = false;
		int minIdx,
			maxIdx,
			middleIndx;
		int result;
		int foundIdx = 0;
		
		minIdx = 0;
		maxIdx = getIndex()-1;
		
		while(minIdx<= maxIdx && !found){
			middleIndx = (maxIdx+minIdx)/2;
			result = getElement(middleIndx).compareTo(userKey);
			if(result == 0 ){
				found = true;
				foundIdx = middleIndx;
			}
			else {
			//	System.out.println(result);
				if(result > 0){
					maxIdx = middleIndx-1;
				}
				else {
					minIdx = middleIndx+1;
				}
			}
		}
		if(!found){
			return -1;
		}
		else {
			return foundIdx;
		}
	}
	
	
}
