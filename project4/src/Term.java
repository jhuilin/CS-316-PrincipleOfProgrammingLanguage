import java.util.*;

class Term
{
	LinkedList<PrimaryItem> primaryItemList;

	Term(LinkedList<PrimaryItem> pItemList)
	{
		primaryItemList = pItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <term>");
		for ( PrimaryItem p : primaryItemList )
			p.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)

	// Evaluate a sequence of primaries operated by * or / using left associativity

	{
		Val termVal = null;

		for ( PrimaryItem p : primaryItemList )
			termVal = p.Eval(state, termVal);
		return termVal;
	}
}