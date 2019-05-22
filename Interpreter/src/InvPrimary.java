import java.util.*;

class InvPrimary extends Primary
{
	Primary primary;

	InvPrimary(Primary p)
	{
		primary = p;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " !");
		primary.printParseTree(indent1);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{		
		if (primary.Eval(state) == null)
			return null;
		if (primary.Eval(state) instanceof BoolVal) 
			return new BoolVal(primary.Eval(state).floatVal() == 0.0);
		System.out.println("Error: ! operator cannot be applied to 1");
		return null;
	}
}