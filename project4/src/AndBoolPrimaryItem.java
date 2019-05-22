import java.util.*;

class AndBoolPrimaryItem extends BoolPrimaryItem

// Represents "&& <boolPrimary>"

{
	// BoolPrimary boolPrimary; inherited from BoolPrimaryItem

	AndBoolPrimaryItem(BoolPrimary bp)
	{
		boolPrimary = bp;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " &&");
		boolPrimary.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val boolTermVal)
	{
		Val boolPrimaryVal = boolPrimary.Eval(state);

		if ( boolTermVal == null || boolPrimaryVal == null )
			return null;
		if ( !(boolTermVal instanceof BoolVal) )
		{
			System.out.println( "Error: && operator cannot be applied to " + boolTermVal.toString() );
			return null;
		}
		if ( !(boolPrimaryVal instanceof BoolVal) )
		{
			System.out.println( "Error: && operator cannot be applied to " + boolPrimaryVal.toString() );
			return null;
		}

		((BoolVal)boolTermVal).val = ((BoolVal)boolTermVal).val && ((BoolVal)boolPrimaryVal).val;
		return boolTermVal;
	}
}