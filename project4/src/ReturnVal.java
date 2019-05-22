import java.util.*;

class ReturnVal extends Var
{
	static final String returnVal_ = "returnVal";

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val returnVal = state.get(returnVal_);
		if ( returnVal != null )
			return returnVal.cloneVal();
		else
		{
			System.out.println( "returnVal does not have a value" );
			return null;
		}
	}

	void M(HashMap<String,Val> state, RightSide rightSide) // interpret assignment returnVal = <right side>
	{
		Val val = rightSide.Eval(state);
		if ( val != null )
			state.put(returnVal_, val);
	}
}