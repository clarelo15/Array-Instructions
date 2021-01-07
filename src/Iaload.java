
public class Iaload extends Instruction{
	int index;
	Iaload(int index){
		this.index = index;
	}
	String instName() {
		return "iaload";
	}
	public String toString()
	{
		return instName() + " " + index;
	}
	void execute() {
		if ( VM.top == VM.opStackSize ) // operand stack overflow
			VM.runtimeError(1, VM.pc, toString(), 0);
		
		int [] tempArray = new int[index];
		for(int i = index-1; i >= 0; i--) {
			int val = ((Int)(VM.opStack[VM.top])).val;
			if(val < 0) 
				VM.runtimeError(5, VM.pc, toString(), val);  
			tempArray[i] = ((Int)(VM.opStack[VM.top])).val;   //(e1, ..., en)
			VM.opStack[VM.top] = null;     //after storing to tempArray, pop the top
			VM.top--;
		}
		IntArray aref = (IntArray)VM.opStack[VM.top];
		if ( aref == null )
			VM.runtimeError(3, VM.pc, toString(), 0);
		VM.opStack[VM.top] = null;
		VM.top--;
		int[] sizelist = aref.sizeList;
		int [] a = aref.a;
		int rankIndex = 0;
		int listSize = 1;
		for(int i = 0; i < index; i++) {
			rankIndex = rankIndex * sizelist[i]+tempArray[i];    //calculate the rank and find the index of a array 
			listSize *= sizelist[i];
		}
		for(int i = 0; i < index; i++) {
			if(tempArray[i]>=sizelist[i])                              //If ei ≥ si for any i, 
				VM.runtimeError(7, VM.pc, toString(), tempArray[i]);   //issue an "index out of bounds" runtime error message
		}
		if (rankIndex > listSize) {
			VM.runtimeError(5, VM.pc, toString(), 0);
		}
		Int data = new Int(a[rankIndex]);
		VM.top++;
		VM.opStack[VM.top] = data;
		VM.pc++;
		VM.updateOpStackPeakSize();
		
	}
}
