import java.util.*;

class DivPrimaryItem extends PrimaryItem

// Represents "/ <primary>"

{
	// Primary primary; inherited from PrimaryItem

	DivPrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " /");
		primary.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val termVal)
	{
		Val primaryVal = primary.Eval(state);

		if ( termVal == null || primaryVal == null )
			return null;
		if ( !termVal.isNumber() )
		{
			System.out.println( "Error: / operator cannot be applied to " + termVal.toString() );
			return null;
		}
		if ( !primaryVal.isNumber() )
		{
			System.out.println( "Error: / operator cannot be applied to " + primaryVal.toString() );
			return null;
		}
		if ( primaryVal.isZero() )
		{
			System.out.println("Error: division by 0");
			return null;
		}
		
		// The result will be float if one or both of the arguments are float.
		
		Class    termClass = termVal.getClass();
		Class primaryClass = primaryVal.getClass();
		
		if ( termClass == IntVal.class && primaryClass == IntVal.class )
		{
			((IntVal)termVal).val = ((IntVal)termVal).val / ((IntVal)primaryVal).val;
			return termVal;
		}
		else if ( termClass == IntVal.class ) // primaryClass == FloatVal.class
		{
			((FloatVal)primaryVal).val = ((IntVal)termVal).val / ((FloatVal)primaryVal).val;
			return primaryVal;
		}
		else // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = termVal.floatVal() / primaryVal.floatVal();
			return termVal;
		}
	}
}