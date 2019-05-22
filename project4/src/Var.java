import java.util.*;

abstract class Var
{
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <var>");
	}

	abstract Val Eval(HashMap<String,Val> state);
	abstract void M(HashMap<String,Val> state, RightSide rightSide); // interpret assignment <var> = <right side>
}
