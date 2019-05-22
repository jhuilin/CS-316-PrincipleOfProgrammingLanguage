import java.util.*;

class Print extends Statement
{
	Expr expr;

	Print(Expr e)
	{
		expr = e;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <print>");
		expr.printParseTree(indent1+" ");
	}

	void M(HashMap<String,Val> state)
	{
		Val exprVal = expr.Eval(state);
		if ( exprVal != null )
			System.out.println(exprVal.toString());
	}
}