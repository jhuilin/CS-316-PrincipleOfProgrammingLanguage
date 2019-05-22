import java.util.*;

class Assignment extends Statement
{
	Var var; // variable on the left side of the assignment
	RightSide rightSide; // right side of the assignment

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

	void M(HashMap<String,Val> state)
	{
		var.M(state, rightSide);  // This M function is polymorphic over inheritance hierarchy of Var class.
	}
}