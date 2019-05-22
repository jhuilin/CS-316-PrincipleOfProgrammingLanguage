import java.util.*;

class E
{
	LinkedList<TermItem> termItemList;

	E(LinkedList<TermItem> tItemList)
	{
		termItemList = tItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <E>");
		for ( TermItem t : termItemList )
			t.printParseTree(indent+" ");
	}
	
	Val Eval(HashMap<String, Val> state) 
	{
		boolean isFloat = false;
		
		if (termItemList.size() < 1)
			return null;
		
		if (termItemList.size() == 1)
			return termItemList.getFirst().Eval(state);
		
		Val oneValue = termItemList.getFirst().Eval(state);
		if (oneValue instanceof BoolVal) 
		{
			if (termItemList.getFirst() instanceof AddTermItem)
				System.out.println("Error: + operator cannot be applied to " + oneValue);
			
			else if (termItemList.getFirst() instanceof SubTermItem)
				System.out.println("Error: - operator cannot be applied to " + oneValue);
			return null;
		}
		else if (!(oneValue instanceof IntVal)) 
			isFloat = true;

		LinkedList<Val> list = new LinkedList<Val>();
		for (int i = 1; i < termItemList.size(); ++i) 
		{
			Val tival = termItemList.get(i).Eval(state);
			if (tival instanceof BoolVal)
				tival = null;
			list.add(tival);
		}
		
		if (oneValue == null || list.contains(null))
			return null;
		
		Double v = oneValue.floatVal();
		for (Val item : list) 
		{
			if (!(item instanceof IntVal))
				isFloat = true;
			v += item.floatVal();
		}	
		if (isFloat)
			return new FloatVal(v);
		return new IntVal(v.intValue());
	}
}