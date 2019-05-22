import java.util.*;

class SingleE extends BoolPrimary
{
	E e;

	SingleE(E e)
	{
		this.e = e;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		e.printParseTree(indent+" ");
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return e.Eval(state);
	}
}