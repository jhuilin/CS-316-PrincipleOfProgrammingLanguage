import java.util.*;

abstract class PrimaryItem
{
	Primary primary;

	abstract void printParseTree(String indent);

	abstract Val Eval(HashMap<String, Val> state);
}