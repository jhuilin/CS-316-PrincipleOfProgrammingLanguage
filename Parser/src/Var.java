import java.util.HashMap;

abstract class Var 
{
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <var>");
	}
}