import java.util.*;

class SingleTermItem extends TermItem
{
	SingleTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		term.printParseTree(indent);
	}
	
	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return term.Eval(state);
	}
}