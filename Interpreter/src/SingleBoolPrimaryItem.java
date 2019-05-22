import java.util.*;

class SingleBoolPrimaryItem extends BoolPrimaryItem
{
	SingleBoolPrimaryItem(BoolPrimary b)
	{
		boolPrimary = b;
	}

	void printParseTree(String indent)
	{
		boolPrimary.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return boolPrimary.Eval(state);
	}
}