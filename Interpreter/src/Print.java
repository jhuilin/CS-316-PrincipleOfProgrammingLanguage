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

	@Override
	void M(HashMap<String, Val> state) 
	{	
		if (expr.Eval(state) != null)
			System.out.println(expr.Eval(state).toString());
	}
}