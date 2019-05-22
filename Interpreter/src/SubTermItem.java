import java.util.*;

class SubTermItem extends TermItem
{
	SubTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " -");
		term.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{	
		if (term.Eval(state) instanceof BoolVal) 
		{
			System.out.println("Error: - operator cannot be applied to " + term.Eval(state));
			return null;
		}
		if (term.Eval(state) instanceof IntVal)
			return new IntVal(-((IntVal) (term.Eval(state))).val);
		
		return new FloatVal(-(term.Eval(state)).floatVal());


	}
}