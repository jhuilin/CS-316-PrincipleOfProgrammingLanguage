import java.util.*;

class FunDefList
{
	LinkedList<FunDef> funDefList;

	FunDefList(LinkedList<FunDef> f)
	{
		funDefList = f;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <fun def list>");
		for ( FunDef f : funDefList )
		f.printParseTree(indent+" ");
	}
}