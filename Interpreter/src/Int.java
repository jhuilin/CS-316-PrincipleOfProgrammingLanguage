import java.util.*;

class Int extends Primary
{
	int val;

	Int(int i)
	{
		val = i;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		IO.displayln(" " + val);
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return new IntVal(val);
	}
}