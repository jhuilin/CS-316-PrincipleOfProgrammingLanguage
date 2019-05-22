
class FunCallPrimary extends Primary
{
	FunCall funCall;
	
	FunCallPrimary(FunCall f) 
	{
		funCall = f;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <primary>");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <fun call primary>");
		indent1 = indent1 + " ";
		funCall.printParseTree(indent1);
	}
}