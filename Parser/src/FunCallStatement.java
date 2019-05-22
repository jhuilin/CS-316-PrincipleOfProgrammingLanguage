
class FunCallStatement extends Statement
{
	FunCall funCall;
	
	FunCallStatement(FunCall f) 
	{
		funCall = f;
	}
	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent.length() + " <fun call statement>");
		indent1 = indent1 + " ";
		funCall.printParseTree(indent1);
	}
}