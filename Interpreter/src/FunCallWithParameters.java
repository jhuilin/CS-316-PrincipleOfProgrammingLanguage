import java.util.*;

class FunCallWithParameters extends FunCall
{
	ExprList exprList;

	FunCallWithParameters(FunName fName, ExprList eList)
	{
		funName = fName;
		exprList = eList;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";

		super.printParseTree(indent1);
		exprList.printParseTree(indent1+" ");
	}

	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		if (funName.id == null)
			return null;
		FunDef funDef = Parser.funDefMap.get(funName.id.id);
		if (funDef == null)
			return null;
		
		LinkedList<Val> parameters = new LinkedList<Val>();
		exprList.M(state, parameters);
		
		if (parameters.contains(null))
			return null;
		
		HashMap<String, Val> nstate = new HashMap<>();
		Header header = funDef.header;
		header.M(nstate, parameters);
		
		Body body = funDef.body;
		body.M(nstate);
		
		if (nstate.containsKey("returnVal") && nstate.get("returnVal") == null) 
		{
			System.out.println("returnVal does not have a value");
			return null;
		}
		return nstate.get("returnVal");

	}
}