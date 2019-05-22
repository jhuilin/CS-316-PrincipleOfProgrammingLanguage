import java.util.*;

class FunCallWithoutParameters extends FunCall
{
	// FunName funName; inherited from FunCall

	FunCallWithoutParameters(FunName fName)
	{
		funName = fName;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent+" ");		
	}

	Val Eval(HashMap<String,Val> state)
	{
		FunDef funDef = Parser.funDefMap.get(funName.id.id);  // get the FunDef object
		if ( funDef == null)
		{
			System.out.println( "Error: function "+funName.id.id+" is undefined" );
			return null;
		}
		Body body = funDef.body;  // get the body

		HashMap<String,Val> newState = new HashMap<String,Val>();
		body.M(newState);
		return Parser.returnVal_.Eval(newState);		
	}

	void M(HashMap<String,Val> state)
	{
		FunDef funDef = Parser.funDefMap.get(funName.id.id);  // get the FunDef object
		if ( funDef == null)
		{
			System.out.println( "Error: function "+funName.id.id+" is undefined" );
			return;
		}
		Body body = funDef.body;  // get the body

		HashMap<String,Val> newState = new HashMap<String,Val>();
		body.M(newState);		
	}
}