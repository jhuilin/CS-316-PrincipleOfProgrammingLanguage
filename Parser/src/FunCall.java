
class FunCall 
{
	FunName funName;
	ExprList exprList;
	
	FunCall(FunName name, ExprList list) 
	{
		funName = name;
		exprList = list;
	}
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <fun call>");
		String indent1 = indent + " ";
		funName.printParseTree(indent1);
		if(exprList != null)
			exprList.printParseTree(indent1);
	}
}