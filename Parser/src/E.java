import java.util.LinkedList;

class E 
{
	LinkedList<TermItem> termList;
	
	E(LinkedList<TermItem> list) 
	{
		termList = list;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <E>");
		String indent1 = indent + " ";
        for ( TermItem t : termList )
            t.printParseTree(indent1);
	}
}