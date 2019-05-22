
class SubTermItem extends TermItem
{
	SubTermItem(Term t) 
	{
		term = t;
	}

	public void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " -");
        term.printParseTree(indent);
	}
}