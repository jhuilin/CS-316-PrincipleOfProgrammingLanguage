import java.util.*;

class E
{
	LinkedList<TermItem> termItemList;

	E(LinkedList<TermItem> tItemList)
	{
		termItemList = tItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <E>");
		for ( TermItem t : termItemList )
			t.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)

	// Evaluate a sequence of terms operated by + or - using left associativity

	{
		Val eVal = null;

		for ( TermItem t : termItemList )
			eVal = t.Eval(state, eVal);
		return eVal;
	}
}