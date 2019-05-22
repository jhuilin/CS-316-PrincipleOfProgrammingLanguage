
class Block extends Statement
{
	SList sList;

	Block(SList list)
	{
		sList = list;
	}

	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent.length() + " <block>");
		indent1 = indent1 + " ";
		sList.printParseTree(indent1);
	}
}
