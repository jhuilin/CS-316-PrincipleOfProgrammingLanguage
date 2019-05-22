import java.util.*;

class If2 extends Cond
{

	Statement statement2;

	If2(Expr e, Statement s1, Statement s2)
	{
		expr = e;
		statement1 = s1;
		statement2 = s2;
	}

	void printParseTree(String indent)
	{
		String indent2 = indent+"  ";
		super.printParseTree(indent);
		IO.displayln(indent2 + indent2.length() + " if");
		expr.printParseTree(indent2);
		statement1.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " else");
		statement2.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) 
	{
		if (expr.Eval(state) instanceof BoolVal)
			if (((BoolVal)expr.Eval(state)).val) 
				statement1.M(state);
			else 
				statement2.M(state);
	}	
}
