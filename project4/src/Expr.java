import java.util.*;

class Expr
{
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btItemList)
	{
		boolTermItemList = btItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <expr>");
		for ( BoolTermItem bt : boolTermItemList )
			bt.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)

	// Evaluate a sequence of boolTerms operated by || using left associativity

	{
		Val exprVal = null;

		for ( BoolTermItem t : boolTermItemList )
			exprVal = t.Eval(state, exprVal);
		return exprVal;
	}
}
