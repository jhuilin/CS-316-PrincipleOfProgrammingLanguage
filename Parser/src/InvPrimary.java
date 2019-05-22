
class InvPrimary extends Primary
{
	Primary primary;
	
	InvPrimary(Primary p)
	{
		primary = p;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <primary>");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " !");
		primary.printParseTree(indent1);
	}
}