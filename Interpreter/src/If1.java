import java.util.*;

class If1 extends Cond
{

	If1(Expr e, Statement s)
	{
		expr = e;
		statement1 = s;
	}
	
	void printParseTree(String indent)
	{
		String indent2 = indent+"  ";
		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) 
	{
		if (expr.Eval(state) instanceof BoolVal)
			if (((BoolVal)expr.Eval(state)).val)
				statement1.M(state);
	}
}