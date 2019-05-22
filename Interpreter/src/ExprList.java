import java.util.*;

class ExprList
{
	LinkedList<Expr> exprList;

	ExprList(LinkedList<Expr> l)
	{
		exprList = l;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <expr list>");
		for ( Expr expr : exprList )
			expr.printParseTree(indent+" ");
	}

	void M(HashMap<String, Val> state, LinkedList<Val> parameters) 
	{
		for (int i = 0; i < exprList.size(); ++i)
			parameters.add(exprList.get(i).Eval(state));
	}
}