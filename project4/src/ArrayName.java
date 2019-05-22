import java.util.*;

class ArrayName
{
	Id id;

	ArrayName(Id ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <array name>");
		id.printParseTree();
	}
}