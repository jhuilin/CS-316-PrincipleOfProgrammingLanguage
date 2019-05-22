
class IntPrimary extends Primary
{
	int integer;
	
	IntPrimary(int i) 
	{
		integer = i;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <primary> " + integer);
	}
}