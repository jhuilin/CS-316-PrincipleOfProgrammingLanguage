import java.util.*;

class AddTermItem extends TermItem
{

	AddTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		if (term.Eval(state) instanceof BoolVal) 
		{
			System.out.println("Error: + operator cannot be applied to " + term.Eval(state));
			return null;	
		}
		return (term.Eval(state));
	}
}