import java.util.*;

class FunCallWithParameters extends FunCall
{
	// FunName funName; inherited from FunCall

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

	Val Eval(HashMap<String,Val> state)
	{
		FunDef funDef = Parser.funDefMap.get(funName.id.id);  // get the FunDef object
		if ( funDef == null)
		{
			System.out.println( "Error: function "+funName.id.id+" is undefined" );
			return null;
		}
		Header header = funDef.header; // get the header
		if ( header instanceof HeaderWithoutParameters )
		{
			System.out.println( "Error: function "+funName.id.id+" is defined without formal parameters" );
			return null;
		}
		LinkedList<Parameter> pList = ((HeaderWithParameters)header).parameterList.parameterList; // get the formal parameter list
		Body body = funDef.body;  // get the body
		LinkedList<Expr> eList = exprList.exprList; // get the actual parameter expression list

		HashMap<String,Val> newState = new HashMap<String,Val>();
		Iterator<Parameter> pIterator = pList.iterator(); // iterator over the formal parameters
		Iterator<Expr> eIterator = eList.iterator(); // iterator over the actual parameter expressions

		while ( pIterator.hasNext() && eIterator.hasNext() ) // form a new function-call state "newState"
		{
			Val exprVal = eIterator.next().Eval(state); // evaluate next actual parameter expression
			if ( exprVal == null )
				return null;
			newState.put(pIterator.next().id.id, exprVal);  // bind next formal parameter to exprVal
		}

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
		Header header = funDef.header; // get the header
		if ( header instanceof HeaderWithoutParameters )
		{
			System.out.println( "Error: function "+funName.id.id+" is defined without formal parameters" );
			return;
		}
		LinkedList<Parameter> pList = ((HeaderWithParameters)header).parameterList.parameterList; // get the formal parameter list
		Body body = funDef.body;  // get the body
		LinkedList<Expr> eList = exprList.exprList; // get the actual parameter expression list

		HashMap<String,Val> newState = new HashMap<String,Val>();
		Iterator<Parameter> pIterator = pList.iterator(); // iterator over the formal parameters
		Iterator<Expr> eIterator = eList.iterator(); // // iterator over the actual parameter expressions

		while ( pIterator.hasNext() && eIterator.hasNext() ) // form a new function-call state "newState"
		{
			Val exprVal = eIterator.next().Eval(state); // evaluate next actual parameter expression
			if ( exprVal == null )
				return;
			newState.put(pIterator.next().id.id, exprVal);  // bind next formal parameter to exprVal
		}

		body.M(newState);		
	}
}