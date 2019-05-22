
class IdVar extends Var
{
	String Id;
	
	IdVar(String i) 
	{
		Id = i;
	}	
	void printParseTree(String indent) 
	{
		super.printParseTree(indent);
		String indent1 = indent + " ";
		if("returnVal".equals(Id)) 
		{
			IO.displayln(indent1 + indent1.length() + " " + Id);
		}
		else 
		{
			IO.displayln(indent1 + indent1.length() + " <id var> " + Id);
		}
	}
}