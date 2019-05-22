
class SingleTermItem extends TermItem
{
	
	SingleTermItem(Term t) 
	{
		term = t;
	}

	public void printParseTree(String indent) 
	{
		term.printParseTree(indent);
	}	
}