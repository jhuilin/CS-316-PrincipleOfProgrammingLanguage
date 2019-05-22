import java.util.LinkedList;

class ExprList 
{
	LinkedList<Expr> exprList;
	
	ExprList(LinkedList<Expr> list) 
	{
		exprList = list;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <expr list>");
		String indent1 = indent + " ";
		for(Expr e : exprList) 
		{
			e.printParseTree(indent1);
		}
	}
}