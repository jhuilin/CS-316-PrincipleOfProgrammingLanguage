import java.util.*;

class OrBoolTermItem extends BoolTermItem

// Represents "|| <boolTerm>"

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	OrBoolTermItem(BoolTerm bt)
	{
		boolTerm = bt;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val exprVal)
	{
		Val boolTermVal = boolTerm.Eval(state);

		if ( exprVal == null || boolTermVal == null )
			return null;
		if ( !(exprVal instanceof BoolVal) )
		{
			System.out.println( "Error: || operator cannot be applied to " + exprVal.toString() );
			return null;
		}
		if ( !(boolTermVal instanceof BoolVal) )
		{
			System.out.println( "Error: || operator cannot be applied to " + boolTermVal.toString() );
			return null;
		}

		((BoolVal)exprVal).val = ((BoolVal)exprVal).val || ((BoolVal)boolTermVal).val;
		return exprVal;
	}
}