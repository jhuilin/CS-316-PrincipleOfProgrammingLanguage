
class ExprRightSide extends RightSide
{
	Expr expr;
	
	ExprRightSide(Expr e) 
	{
		expr = e;
	}
	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <expr right side>");
		indent1 = indent1 + " ";
		expr.printParseTree(indent1);
	}
}