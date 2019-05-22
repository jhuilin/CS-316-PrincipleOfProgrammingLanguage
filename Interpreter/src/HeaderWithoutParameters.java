import java.util.*;

class HeaderWithoutParameters extends Header
{

	HeaderWithoutParameters(FunName f)
	{
		funName = f;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
	}

	@Override
	void M(HashMap<String, Val> nState, LinkedList<Val> parameters) {}
}