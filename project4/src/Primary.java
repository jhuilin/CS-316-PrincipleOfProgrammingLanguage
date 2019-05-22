import java.util.*;

abstract class Primary
{
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <primary>");
	}

	abstract Val Eval(HashMap<String,Val> state);
}