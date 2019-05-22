import java.util.*;

class IdVar extends Var
{
	Id id;

	IdVar(Id ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.display(indent1 + indent1.length() + " <id var>");
		id.printParseTree();
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val idVal = state.get(id.id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id.id+" does not have a value" );
			return null;
		}
	}

	void M(HashMap<String,Val> state, RightSide rightSide) // interpret assignment <id var> = <right side>
	{
		Val val = rightSide.Eval(state);
		if ( val != null )
			state.put(id.id, val);
	}
}