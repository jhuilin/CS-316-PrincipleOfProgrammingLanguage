
class Body
{
	SList sList;

	Body(SList list)
	{
		sList = list;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <body>");
		sList.printParseTree(indent1);
	}
}
