
class FloatE extends Primary
{
	float Float;	
	FloatE(float f) 
	{
		Float = f;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <primary> " + Float);
	}
}