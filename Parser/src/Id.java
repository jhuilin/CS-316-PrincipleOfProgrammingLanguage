
class Id extends Primary
{
	String Id;
	
	Id(String i) 
	{
		Id = i;
	}
	
	void printParseTree(String indent) 
	{
		IO.display(indent + indent.length() + " <primary> " + Id);
	}
}