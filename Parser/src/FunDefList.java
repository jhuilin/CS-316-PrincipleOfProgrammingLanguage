import java.util.LinkedList;

class FunDefList 
{
	LinkedList<FunDef> funDefList;
	
	FunDefList(LinkedList<FunDef> list)
	{
		funDefList = list;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <fun def list>");
		String indent1 = indent + " ";
		for(FunDef f : funDefList) 
		{
			f.printParseTree(indent1);
		}
	}
}