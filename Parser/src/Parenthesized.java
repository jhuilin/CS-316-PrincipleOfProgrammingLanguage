
class Parenthesized extends Primary
{
	Expr expr;
	
	Parenthesized(Expr e) 
	{
		expr = e;
	}
	
	void printParseTree(String indent){
		IO.displayln(indent + indent.length() + " <primary> ");
		String indent1 = indent + " ";
		expr.printParseTree(indent1);
	}
}