import java.util.*;

class Body
{
	SList sList;

	Body(SList s)
	{
		sList = s;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <body>");
		sList.printParseTree(indent+" ");
	}

	void M(HashMap<String,Val> state)
	{
		sList.M(state);
	}
}
