import java.util.*;

class ReturnVal extends Var
{
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{		
		if(state.containsKey("returnVal"))
			if(state.get("returnVal") != null)
				return state.get("returnVal");
		System.out.println("returnVal does not have a value");
		return null;
	}
}