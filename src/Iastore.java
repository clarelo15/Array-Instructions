
public class Iastore extends Instruction{
	int index;
	Iastore(int index){
		this.index = index;
	}
	String instName() {
		return "iastore";
	}
	public String toString()
	{
		return instName() + " " + index;
	}
	void execute()
	{
		int e = ((Int)(VM.opStack[VM.top])).val;    // e = stack[top]
		VM.opStack[VM.top] = null;
		VM.top--;
		
		int [] tempArray = new int[index];
		for(int i = index-1; i >= 0; i--) {
			int val = ((Int)(VM.opStack[VM.top])).val;
			if(val < 0)                                     //If ei < 0 or for any i,
				VM.runtimeError(5, VM.pc, toString(), val); //issue an "index out of bounds" runtime error message
			tempArray[i] = ((Int)(VM.opStack[VM.top])).val;
			VM.opStack[VM.top] = null;
			VM.top--;
		}
		
		IntArray aref = (IntArray)VM.opStack[VM.top];      ////aref = stack[top−n−1]
		VM.opStack[VM.top] = null;
		VM.top--;
		int[] sizelist = aref.sizeList;
		int [] a = aref.a;
		int rankIndex = 0;
		int listSize = 1;
		for(int i = 0; i < index; i++) {
			rankIndex = rankIndex * sizelist[i]+tempArray[i];
			listSize *= sizelist[i];
		}
		for(int i = 0; i < index; i++) {
			if(tempArray[i]>=sizelist[i])                           //If ei ≥ si for any i,
				VM.runtimeError(5, VM.pc, toString(), tempArray[i]);//issue an "index out of bounds" runtime error message
		}
		if (rankIndex > listSize) {
			VM.runtimeError(5, VM.pc, toString(), 0);
		}
		a[rankIndex] = e;     //Assign integer e to a[rank(e1, ..., en)]
		VM.pc++;
	}
}
