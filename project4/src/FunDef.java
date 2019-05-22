import java.util.*;

class FunDef
{
	Header header;
	Body body;

	FunDef(Header h, Body b)
	{
		header = h;
		body = b;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		IO.displayln(indent + indent.length() + " <fun def>");
		header.printParseTree(indent1);
		body.printParseTree(indent1);
	}
}