
public class NewFloatArray extends Instruction{
	int index;
	NewFloatArray(int index){
		this.index = index;
		
	}
	String instName() {
		return "newFloatArray";
	}
	public String toString()
	{
		return instName() + " " + index;
	}
	void execute()
	{
		
		int size = 1;
		int [] sizeList = new int[index];
		for(int i = index-1; i >= 0; i--) {
			int val = ((Int)(VM.opStack[VM.top])).val;
			if(val < 0)     //if any value is negative, then print runtime error
				VM.runtimeError(6, VM.pc, toString(), val);
			sizeList[i] = val;
			size *= val;
			VM.opStack[VM.top] = null;   //pop the top each time
			VM.top--;
		}
		double a[] = new double[size];
		FloatArray aref = new FloatArray(a, sizeList);
		VM.top++;
		VM.opStack[VM.top] = aref;
		VM.pc++;
				
	}
}
