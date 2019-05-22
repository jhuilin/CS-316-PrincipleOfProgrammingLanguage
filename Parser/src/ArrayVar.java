
class ArrayVar extends Var
{
	ArrayName arrayName;
	EList eList;

	ArrayVar(ArrayName name, EList list)
	{
		arrayName = name;
		eList = list;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array var>");
		indent1 = indent1 + " ";
		arrayName.printParseTree(indent1);
		eList.printParseTree(indent1);
	}
}
