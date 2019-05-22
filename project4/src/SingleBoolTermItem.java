import java.util.*;

class SingleBoolTermItem extends BoolTermItem

// Represents the first <boolTerm> in <Expr>

{
	// BoolTerm boolTerm; inherited from BoolTermItem

	SingleBoolTermItem(BoolTerm bt)
	{
		boolTerm = bt;
	}

	void printParseTree(String indent)
	{
		boolTerm.printParseTree(indent);
	}

	Val Eval(HashMap<String,Val> state, Val exprVal)
	{
		exprVal = boolTerm.Eval(state);
		return exprVal;
	}
}