
class ReturnVal extends Var
{
	ReturnVal(){}
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " returnVal");
	}
}
