
public class FloatArray extends Data{
	int[] sizeList;
	double[] a;
	FloatArray(double[] a , int[] sizeList){
		this.a = a;
		this.sizeList = sizeList;
	}
	Data cloneData() {
		return new FloatArray(a, sizeList);
	}
	public String toString() {
		String s= "float array of size list [";
		for (int i = 0; i< sizeList.length ; i++) {
			s += sizeList[i];
			if(i < sizeList.length-1)	
				s+= ", ";
		}
		return s+"]";
	}
}
