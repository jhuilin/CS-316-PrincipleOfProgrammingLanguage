import java.util.*;

class SingleE extends BoolPrimary
{
	E e;

	SingleE(E e_)
	{
		e = e_;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		e.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String,Val> state)
	{
		return e.Eval(state);
	}
}