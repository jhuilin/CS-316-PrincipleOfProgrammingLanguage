
abstract class CompBoolPrimary extends BoolPrimary
{
	E e1;
	E e2;

	CompBoolPrimary(E a, E b)
	{
		e1 = a;
		e2 = b;
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