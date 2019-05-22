import java.util.*;

class ParameterList
{
	LinkedList<Parameter> parameterList;

	ParameterList(LinkedList<Parameter> p)
	{
		parameterList = p;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <parameter list>");
		for ( Parameter p : parameterList )
			p.printParseTree(indent+" ");
	}

	void M(HashMap<String, Val> state, LinkedList<Val> parameters) 
	{
		if (parameterList.size() == parameters.size())
			for (int i = 0; i < parameterList.size(); ++i) 
				state.put(parameterList.get(i).id.id, parameters.get(i));
		return;
	}
}