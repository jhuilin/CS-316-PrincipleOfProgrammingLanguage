import java.util.*;

class OrBoolTermItem extends BoolTermItem

{

	OrBoolTermItem(BoolTerm b)
	{
		boolTerm = b;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return boolTerm.Eval(state);
	}
}