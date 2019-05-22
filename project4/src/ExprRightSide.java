import java.util.*;

class ExprRightSide extends RightSide
{
	Expr expr;

	ExprRightSide(Expr e)
	{
		expr = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <expr right side>");
		expr.printParseTree(indent1+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return expr.Eval(state);
	}
}
