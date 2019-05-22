import java.util.*;

class NegPrimary extends Primary
{
	Primary primary;

	NegPrimary(Primary p)
	{
		primary = p;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " -");
		primary.printParseTree(indent1);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		
		if (primary.Eval(state) == null)
			return null;
		if (primary.Eval(state) instanceof BoolVal) 
		{
			System.out.println("Error: unary - operator cannot be applied to " + primary.Eval(state));
			return null;
		}	
		Double v = primary.Eval(state).floatVal();
		if (primary.Eval(state) instanceof FloatVal)
			return new FloatVal(-v.floatValue());
		return new IntVal(-v.intValue());
	}
}