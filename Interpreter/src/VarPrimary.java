import java.util.*;

class VarPrimary extends Primary
{
	Var var;

	VarPrimary(Var v)
	{
		var = v;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		IO.displayln("");
		var.printParseTree(indent+" ");
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return var.Eval(state);
	}
}