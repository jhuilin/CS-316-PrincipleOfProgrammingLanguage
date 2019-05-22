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

	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = primary.Eval(state);
		if ( primaryVal == null )
			return null;
		if ( primaryVal instanceof IntVal )
		{
			((IntVal)primaryVal).val = -((IntVal)primaryVal).val;
			return primaryVal;
		}
		if ( primaryVal instanceof FloatVal )
		{ 
			((FloatVal)primaryVal).val = -((FloatVal)primaryVal).val;
			return primaryVal;
		}
		System.out.println( "Error: unary - operator cannot be applied to " + primaryVal.toString() );
		return null;
	}
}