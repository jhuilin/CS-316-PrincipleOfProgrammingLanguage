
class While extends Statement
{
	Expr expr;
	Statement statement;
	
	While(Expr e, Statement s) 
	{
		expr = e;
		statement = s;
	}
	
	public void printParseTree(String indent) {
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <while>");
		indent1 = indent1 + " ";
		IO.displayln(indent1 + indent1.length() + " while");
		expr.printParseTree(indent1);
		statement.printParseTree(indent1);
	}
}
