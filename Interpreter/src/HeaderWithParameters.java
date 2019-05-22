import java.util.*;

class HeaderWithParameters extends Header
{
	ParameterList parameterList;

	HeaderWithParameters(FunName f, ParameterList list)
	{
		funName = f;
		parameterList = list;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
		parameterList.printParseTree(indent+" ");
	}

	@Override
	void M(HashMap<String, Val> state, LinkedList<Val> parameters) 
	{
		parameterList.M(state, parameters);
	}
}