import java.util.*;

class FunName
{
	Id id;

	FunName(Id i)
	{
		id = i;
	}

	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <fun name>");
		id.printParseTree();
	}
}