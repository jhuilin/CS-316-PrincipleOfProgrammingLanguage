import java.util.*;

class FunCallPrimary extends Primary
{
	FunCall funCall;

	FunCallPrimary(FunCall f)
	{
		funCall = f;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " <fun call primary>");
		funCall.printParseTree(indent1);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return funCall.Eval(state);
	}
}