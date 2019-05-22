import java.util.*;

class Parameter
{
	Id id;

	Parameter(Id i)
	{
		id = i;
	}

	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <parameter>");
		id.printParseTree();
	}
}