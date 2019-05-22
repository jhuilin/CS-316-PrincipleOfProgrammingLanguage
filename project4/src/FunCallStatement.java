import java.util.*;

class FunCallStatement extends Statement
{
	FunCall funCall;

	FunCallStatement(FunCall fCall)
	{
		funCall = fCall;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <fun call statement>");
		funCall.printParseTree(indent1);
	}

	void M(HashMap<String,Val> state)
	{
		funCall.M(state);
	}
}