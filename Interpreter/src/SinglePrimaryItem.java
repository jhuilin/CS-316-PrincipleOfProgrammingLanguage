import java.util.*;

class SinglePrimaryItem extends PrimaryItem
{

	SinglePrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		primary.printParseTree(indent);
	}
	
	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return primary.Eval(state);
	}
}