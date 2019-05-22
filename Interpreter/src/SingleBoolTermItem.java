import java.util.*;

class SingleBoolTermItem extends BoolTermItem
{

	SingleBoolTermItem(BoolTerm b)
	{
		boolTerm = b;
	}

	void printParseTree(String indent)
	{
		boolTerm.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return boolTerm.Eval(state);
	}
}