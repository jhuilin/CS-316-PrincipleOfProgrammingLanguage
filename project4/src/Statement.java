import java.util.*;

abstract class Statement
{
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <statement>");
	}

	abstract void M(HashMap<String,Val> state);
}
