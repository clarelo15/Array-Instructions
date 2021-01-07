 
public class IntArray extends Data{
	int[] a;
	int[] sizeList;
	IntArray(int[] a , int[] sizeList){
		this.a = a;
		this.sizeList = sizeList;
	}
	Data cloneData() {
		return new IntArray(a, sizeList);
	}
	@Override
	public String toString() {
		String s= "int array of size list [";
		for (int i = 0; i< sizeList.length ; i++) {
			s += sizeList[i];
			if(i < sizeList.length-1)	
				s+= ", ";
		}
		return s+"]";
	}
}
