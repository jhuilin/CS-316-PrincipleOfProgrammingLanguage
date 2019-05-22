
class Parameter 
{
	String Id;
	
	Parameter(String i) 
	{
		Id = i;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <parameter> " + Id);
	}
}