import java.util.LinkedList;

class EList 
{	
	LinkedList<E> EList;
	EList(LinkedList<E> list) 
	{
		EList = list;
	}

	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <E list>");	
		String indent1 = indent + " ";
		for(E e : EList) 
		{
			e.printParseTree(indent1);
		}	
	}
}