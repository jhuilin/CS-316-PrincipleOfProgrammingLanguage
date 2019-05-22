import java.util.*;

class SList
{
	LinkedList<Statement> sList;

	SList(LinkedList<Statement> sl)
	{
		sList = sl;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <s list>");
		for ( Statement s : sList )
			s.printParseTree(indent+" ");
	}

	void M(HashMap<String,Val> state)
	{
		for ( Statement s : sList )
			s.M(state);
	}
}