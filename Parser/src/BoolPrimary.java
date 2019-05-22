import java.util.LinkedList;

class BoolPrimary
{
	E first;
	LinkedList<CompOp> compOp;
	LinkedList<E> next;

	BoolPrimary(E f, LinkedList<CompOp> co, LinkedList<E> s)
	{
		first = f;
		compOp = co;
		next = s;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolPrimary>");
		String indent1 = indent + " ";
		first.printParseTree(indent1);
		for(int i = 0; i < compOp.size(); i++)
		{
			CompOp co = compOp.get(i);
			E e = next.get(i);
			co.printParseTree(indent1);
			e.printParseTree(indent1);
		}
	}
}
