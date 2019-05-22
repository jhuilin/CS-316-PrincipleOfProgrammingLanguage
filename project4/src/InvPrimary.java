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

	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = primary.Eval(state);
		if ( primaryVal == null )
			return null;
		if ( primaryVal instanceof BoolVal )
		{
			((BoolVal)primaryVal).val = !((BoolVal)primaryVal).val;
			return primaryVal;
		}
		System.out.println( "Error: ! operator cannot be applied to " + primaryVal.toString() );
		return null;
	}
}