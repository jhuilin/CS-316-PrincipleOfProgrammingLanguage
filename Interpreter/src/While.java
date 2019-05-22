import java.util.*;

class While extends Statement
{
	Expr expr;
	Statement statement;

	While(Expr e, Statement s)
	{
		expr = e;
		statement = s;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		String indent2 = indent+"  ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <while>");
		IO.displayln(indent2 + indent2.length() + " while");
		expr.printParseTree(indent2);
		statement.printParseTree(indent2);
	}

	void M(HashMap<String,Val> state)

	// The M function is implemented by a while-loop instead of recursion to avoid runtime stack buildup.
	// The null value represents the runtime error value.
 
	{
		Val exprVal = expr.Eval(state);
		while ( exprVal != null )
		{
			if ( exprVal instanceof BoolVal )
				if ( ((BoolVal)exprVal).val )
				{
					statement.M(state);
					exprVal = expr.Eval(state);
				}
				else
					return;
			else
			{
				System.out.println( "Error: Boolean expression of while statement evaluated to non-Boolean value: " + exprVal.toString() );
				return;
			}
		}
	}
}
