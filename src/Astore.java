
public class Astore extends Instruction{
	int index;
	Astore(int index){
		this.index = index;
	}
	String instName() {
		return "astore";
	}
	public String toString()
	{
		return instName() + " " + index;
	}
	void execute()
	{
		AR topAR = VM.runtimeStack[VM.topR];
		topAR.vars[index] = VM.opStack[VM.top];
		VM.opStack[VM.top] = null;
		VM.top--;
		VM.pc++;
	}
}
