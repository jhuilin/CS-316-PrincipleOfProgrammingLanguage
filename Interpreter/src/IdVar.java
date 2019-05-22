import java.util.*;

class IdVar extends Var
{
	Id id;

	IdVar(Id i)
	{
		id = i;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		super.printParseTree(indent);
		IO.displayln("");
		IO.display(indent1 + indent1.length() + " <id var>");
		id.printParseTree();
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		return id.Eval(state);
	}
}