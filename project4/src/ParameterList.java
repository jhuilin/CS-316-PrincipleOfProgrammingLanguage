import java.util.*;

class ParameterList
{
	LinkedList<Parameter> parameterList;

	ParameterList(LinkedList<Parameter> pl)
	{
		parameterList = pl;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <parameter list>");
		for ( Parameter p : parameterList )
			p.printParseTree(indent+" ");
	}
}