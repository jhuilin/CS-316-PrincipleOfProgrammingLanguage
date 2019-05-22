import java.util.*;

class Id
{
	String id;

	Id(String ident)
	{
		id = ident;
	}

	void printParseTree()
	{
		IO.displayln(" " + id);
	}

	Val Eval(HashMap<String, Val> state) 
	{	
		if(state.containsKey(id))
			return state.get(id).cloneVal();
		else
			System.out.println("variable " + id + " does not have a value");
		return null;
	}
}