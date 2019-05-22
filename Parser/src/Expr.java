import java.util.LinkedList;

class Expr 
{
	LinkedList<BoolTerm> boolTermList;	
	Expr(LinkedList<BoolTerm> list) 
	{
		boolTermList = list;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <expr>");
		String indent1 = indent + " ";
		BoolTerm b = boolTermList.getFirst();
		b.printParseTree(indent1);
		for(int i = 1; i < boolTermList.size(); ++i) 
		{
			b = boolTermList.get(i);
			IO.displayln(indent1 + indent1.length() + " ||");
			b.printParseTree(indent1);
		}
	}

}