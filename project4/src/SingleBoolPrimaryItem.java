import java.util.*;

class SingleBoolPrimaryItem extends BoolPrimaryItem

// Represents the first <boolPrimary> in <boolTerm>

{
	// BoolPrimary boolPrimary; inherited from BoolPrimaryItem

	SingleBoolPrimaryItem(BoolPrimary bp)
	{
		boolPrimary = bp;
	}

	void printParseTree(String indent)
	{
		boolPrimary.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val boolTermVal)
	{
		boolTermVal = boolPrimary.Eval(state);
		return boolTermVal;
	}
}