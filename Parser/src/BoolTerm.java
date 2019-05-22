import java.util.LinkedList;

class BoolTerm
{
	LinkedList<BoolPrimary> boolPrimaryList;

	BoolTerm(LinkedList<BoolPrimary> list)
	{
		boolPrimaryList = list;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolTerm>");
		String indent1 = indent + " ";
		BoolPrimary b = boolPrimaryList.getFirst();
		b.printParseTree(indent1);
		for(int i = 1; i < boolPrimaryList.size(); i++)
		{
			b = boolPrimaryList.get(i);
			IO.displayln(indent1 + indent1.length() + " &&");
			b.printParseTree(indent1);
		}
	}
}
