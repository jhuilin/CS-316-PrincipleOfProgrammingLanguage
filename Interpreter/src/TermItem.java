import java.util.*;

abstract class TermItem
{
	Term term;

	abstract void printParseTree(String indent);

	abstract Val Eval(HashMap<String, Val> state);
}