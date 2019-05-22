import java.util.*;

class FunName
{
	Id id;

	FunName(Id ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <fun name>");
		id.printParseTree();
	}
}