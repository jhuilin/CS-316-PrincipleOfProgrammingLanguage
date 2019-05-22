import java.util.*;

class EList
{
	LinkedList<E> eList;

	EList(LinkedList<E> el)
	{
		eList = el;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <E list>");
		for ( E e : eList )
			e.printParseTree(indent+" ");
	}
	
	LinkedList<Val> Eval(HashMap<String,Val> state) {
		LinkedList<Val> allVals = new LinkedList<Val>();
		
		for (E e : eList)
			allVals.add(e.Eval(state));
		return allVals;
	}
}