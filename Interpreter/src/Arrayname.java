import java.util.HashMap;

class ArrayName
{
	Id id;

	ArrayName(Id i)
	{
		id = i;
	}

	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <array name>");
		id.printParseTree();
	}
    Val Eval(HashMap<String,Val> state){
        return null;
    }
}