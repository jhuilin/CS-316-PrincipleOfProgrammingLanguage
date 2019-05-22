import java.util.*;

class AndBoolPrimaryItem extends BoolPrimaryItem

{

	AndBoolPrimaryItem(BoolPrimary b)
	{
		boolPrimary = b;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " &&");
		boolPrimary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return boolPrimary.Eval(state);
	}
}