package FinalProject;

public class MyArrayList<T> {

	private T[] t;
	final int MAX_SIZE = 300;
	private int index;
	int pointer;

	// constructors
	public MyArrayList() {
		t = (T[]) new Object[MAX_SIZE];
		index = 0;
		pointer = 0;
	}

	public MyArrayList(int size) {
		t = (T[]) new Object[size];
		index = 0;
		pointer = 0;
	}

	// get length of the array
	public int getLength() {
		return t.length;
	}

	public T[] getArray() {
		T[] obj = (T[]) new Object[t.length];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = t[i];
		}
		return obj;
	}

	public void setArray(T[] anObj) {
		int count = 0;
		for (int i = 0; i < anObj.length; i++) {
			if (anObj[i] != null) {
				t[i] = anObj[i];
				count++;
			}
		}
		if (count < index) {
			for (int i1 = count; i1 < index; i1++) {
				t[i1] = null;
			}
		}
		setIndex(count);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int anIndex) {
		index = anIndex;
	}

	public T getElement(int pos) {
		return t[pos];
	}
	
	public int getPos(T a){
		return isThere(a);
			
	}

	public void setElement(T a, int pos) {
		try {
			if (pos > index || pos < 0) {
				throw new Exception();
			}
			t[pos] = a;
			if (pos == index) {
				index++;
			}
		} catch (Exception o) {
			System.err.println("THe position is not valid!");
		}
	}

	public void swap(int a, int b) throws Exception {
		if (a >= index || b >= index) {
			throw new Exception("Position is not valid.");
		}
		T temp = t[a];
		t[a] = t[b];
		t[b] = temp;

	}

	public String toString() {
		String aStr = "";
		for (int i = 0; i < index; i++) {
			aStr += t[i].toString() + "\n";
		}
		return aStr;
	}

	public boolean equals(MyArrayList anArray) {
		if (index != anArray.getIndex()) {
			return false;
		}
		if (t.length != anArray.getLength()) {
			return false;
		}
		for (int i = 0; i < index; i++) {
			if (!t[i].equals(anArray.getElement(i))) {
				return false;
			}
		}
		return true;
	}

	public void add(T anObj) {
		if (isFull()) {
			moreCapacity(MAX_SIZE);
		}
		t[index] = anObj;
		index++;

	}

	public void insert(T anObj, int pos) throws Exception {
		try {
			if (pos > index || pos < 0) {
				throw new Exception();
			}
			if (isFull()) {
				moreCapacity(MAX_SIZE);
			}
			for (int i = index; i > pos; i--) {
				t[i] = t[i - 1];
			}
			t[pos] = anObj;
			index++;

		} catch (Exception exc) {
			System.err.println("Position is not valid.");
		}

	}

	public int isThere(T anObj) {
		for (int i = 0; i < index; i++) {
			if (t[i].equals(anObj))
				return i;
		}
		return -1;
	}

	public void delete(T anObj) {
		int pos = isThere(anObj);
		if (pos != -1) {
			for (int i = pos; i < index; i++) {
				t[i] = t[i + 1];
			}
			index--;
		}
	}

	public void delete(int pos) throws Exception {
		try {
			if (pos >= index) {
				throw new Exception();
			}
			for (int i = pos; i < t.length - 1; i++) {
				t[i] = t[i + 1];
			}
			index--;

		} catch (Exception o) {
			System.err.println("Wrong position!");

		}

	}

	// isFull()
	public boolean isFull() {
		return index == t.length;
	}

	// isEmpty
	public boolean isEmpty() {
		return index == 0;
	}

	// clear
	public void clear() {
		t = null;
		index = 0;
	}

	// trim()
	public void trim() {
		T[] newArr = (T[]) new Object[index];
		for (int i = 0; i < index; i++) {
			newArr[i] = t[i];
		}

		t = newArr;
		newArr = null;
	}

	// more capacity
	public void moreCapacity(int add) {
		int newSize = add + t.length;
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) new Object[newSize];
		for (int i = 0; i < index; i++) {
			newArr[i] = t[i];
		}
		t = newArr;
		newArr = null;
	}

	// getNext(), reset(), hasNext(),
	public T getNext() {
		if (hasNext()) {
			pointer++;
			return t[pointer - 1];
		}
		return null;
	}

	public void reset() {
		pointer = 0;
		index = 0;
	}

	public boolean hasNext() {
		if (pointer < index) {
			return true;
		}
		return false;
	}


}
