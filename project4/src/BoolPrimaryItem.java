import java.util.*;

abstract class BoolPrimaryItem
{
	BoolPrimary boolPrimary;

	abstract void printParseTree(String indent);
	abstract Val Eval(HashMap<String,Val> state, Val boolTermVal);
}