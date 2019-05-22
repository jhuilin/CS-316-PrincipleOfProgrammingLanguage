
class CompOp 
{
	String op;
	
	CompOp(String o) 
	{
		op = o;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " " + op);
	}
}