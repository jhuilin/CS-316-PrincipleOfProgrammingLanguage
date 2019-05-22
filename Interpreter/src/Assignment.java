import java.util.*;

class Assignment extends Statement
{
	Var var;
	RightSide rightSide;

	Assignment(Var v, RightSide rs)
	{
		var = v;
		rightSide = rs;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		String indent2 = indent+"  ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		var.printParseTree(indent2);
		IO.displayln(indent2 + indent2.length() + " =");
		rightSide.printParseTree(indent2);
	}

	@Override
	void M(HashMap<String, Val> state) 
	{
		if (rightSide instanceof ExprRightSide) 
		{
			if (var instanceof ReturnVal) 
				state.put("returnVal", ((ExprRightSide)rightSide).expr.Eval(state));
			
			else if (var instanceof IdVar) 
				state.put(((IdVar)var).id.id, ((ExprRightSide)rightSide).expr.Eval(state));
		}
	}
}