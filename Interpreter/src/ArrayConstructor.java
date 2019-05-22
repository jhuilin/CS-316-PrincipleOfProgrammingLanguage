import java.util.HashMap;

class ArrayConstructor extends RightSide
{
	EList eList;

	ArrayConstructor(EList l)
	{
		eList = l;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";	
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		eList.printParseTree(indent1+" ");
	}
    Val Eval(HashMap<String,Val> state){
        return null;
    }
}