
class Print extends Statement
{
	Expr expr;
	
	Print(Expr e) 
	{
		expr = e;
	}
	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent.length() + " <print>");
		indent1 = indent1 + " ";
		expr.printParseTree(indent1);
	}
}