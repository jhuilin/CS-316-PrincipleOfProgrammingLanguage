import java.util.*;

class HeaderWithoutParameters extends Header
{
	//FunName funName; inherited from Header

	HeaderWithoutParameters(FunName fName)
	{
		funName = fName;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent);
	}
}