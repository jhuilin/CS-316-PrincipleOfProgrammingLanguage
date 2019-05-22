import java.util.*;

class BoolTerm
{
	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> bpItemList)
	{
		boolPrimaryItemList = bpItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolTerm>");
		for ( BoolPrimaryItem bp : boolPrimaryItemList )
			bp.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)

	// Evaluate a sequence of boolPrimaries operated by && using left associativity

	{
		Val boolTermVal = null;

		for ( BoolPrimaryItem p : boolPrimaryItemList )
			boolTermVal = p.Eval(state, boolTermVal);
		return boolTermVal;
	}
}


