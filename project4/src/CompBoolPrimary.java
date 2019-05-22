import java.util.*;

abstract class CompBoolPrimary extends BoolPrimary
{
	E e1;
	E e2;

	CompBoolPrimary(E e_1, E e_2)
	{
		e1 = e_1;
		e2 = e_2;
	}

	abstract String getOp();
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent);
		e1.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + getOp());
		e2.printParseTree(indent1);
	}
}