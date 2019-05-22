
class Header 
{
	FunName funName;
	ParameterList parameterList;
	
	Header(FunName name, ParameterList list) 
	{
		funName = name;
		parameterList = list;
	}
	
	void printParseTree(String indent) 
	{
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <header>");
		funName.printParseTree(indent1);
		if(parameterList !=null)
			parameterList.printParseTree(indent1);
	}
}