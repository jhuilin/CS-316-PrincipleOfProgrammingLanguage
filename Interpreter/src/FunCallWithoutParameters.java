import java.util.*;

class FunCallWithoutParameters extends FunCall
{

	FunCallWithoutParameters(FunName f)
	{
		funName = f;
	}
	
	void printParseTree(String indent)
	{
		super.printParseTree(indent+" ");		
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		if (funName.id == null)
			return null;
		
		FunDef funDef = Parser.funDefMap.get(funName.id.id);
		if (funDef == null)
			return null;
		
		HashMap<String,Val> nState = new HashMap<String,Val>();
		Body body = funDef.body;
		body.M(nState);	
		return nState.get("returnVal");
	}
}