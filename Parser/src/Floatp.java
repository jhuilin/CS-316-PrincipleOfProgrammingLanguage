
class Floatp extends Primary
{
	float Float;
	
	Floatp(float f) 
	{
		Float = f;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <primary> " + Float);
	}
}