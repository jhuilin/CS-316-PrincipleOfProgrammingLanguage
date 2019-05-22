import java.util.*;

abstract class BoolTermItem
{
	BoolTerm boolTerm;

	abstract void printParseTree(String indent);

	abstract Val Eval(HashMap<String, Val> state);
}