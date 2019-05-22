import java.util.LinkedList;

class Term 
{
	LinkedList<Primary> primaryItemList;
	
    Term(LinkedList<Primary> list)
    {
        primaryItemList = list;
    }
    
    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <term>");
        for (Primary p : primaryItemList)
            p.printParseTree(indent+" ");
    }
}