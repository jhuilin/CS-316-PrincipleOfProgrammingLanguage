
class ArrayConstructor extends RightSide
{
	EList eList;
	
	ArrayConstructor(EList list) 
	{
		eList = list;
	}
	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);	
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		indent1 = indent1 + " ";
		eList.printParseTree(indent1);
	}
}