
public class Aload extends Instruction{
	int index;
	Aload(int index){
		this.index = index;
	}
	String instName() {
		return "aload";
	}
	public String toString()
	{
		return instName() + " " + index;
	}
	void execute() {
		AR topAR = VM.runtimeStack[VM.topR];
		VM.top++;
		if ( VM.top == VM.opStackSize ) // operand stack overflow
			VM.runtimeError(1, VM.pc, toString(), 0);
		Data val = topAR.vars[index];
		
		if ( val == null )
			VM.runtimeError(3, VM.pc, toString(), 0);
		VM.opStack[VM.top] = val.cloneData();
		VM.pc++;
		VM.updateOpStackPeakSize();
	}

}
