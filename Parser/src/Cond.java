import java.util.LinkedList;

class Cond extends Statement
{
	Expr expr;
	Statement statement;
	LinkedList<Statement> elseStatements;
	
	Cond(Expr e, Statement s, LinkedList<Statement> es) 
	{
		expr = e;
		statement = s;
		elseStatements = es;
	}
	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <cond>");
		indent1 = indent1 + " ";
		IO.displayln(indent1 + indent1.length() + " if");
		expr.printParseTree(indent1);
		statement.printParseTree(indent1);
		for(Statement s : elseStatements) 
		{
			IO.displayln(indent1 + indent1.length() + " else");
			s.printParseTree(indent1);
		}
	}
}