import java.util.List;

class ParameterList 
{
	List<Parameter> list;
	
	ParameterList(List<Parameter> l) 
	{
		list = l;
	}
	
	void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <parameter list>");
		String indent1 = indent + " ";
		for(Parameter p : list) 
		{
			p.printParseTree(indent1);
		}
	}
}