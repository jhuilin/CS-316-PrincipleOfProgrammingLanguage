
class FunName 
{
	String Id;
	
	FunName(String i) 
	{
		Id = i;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <fun name> " + Id);
	}
}