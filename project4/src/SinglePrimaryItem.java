import java.util.*;

class SinglePrimaryItem extends PrimaryItem

// Represents the first <primary> in <term>

{
	// Primary primary; inherited from PrimaryItem

	SinglePrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		primary.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val termVal)
	{
		termVal = primary.Eval(state);
		return termVal;
	}
}