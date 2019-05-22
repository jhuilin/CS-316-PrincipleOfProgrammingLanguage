
abstract class Cond extends Statement
{
	Expr expr;
	Statement statement1;

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <cond>");
	}
}