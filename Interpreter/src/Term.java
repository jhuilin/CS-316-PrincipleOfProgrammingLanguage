import java.util.*;

class Term
{
	LinkedList<PrimaryItem> primaryItemList;

	Term(LinkedList<PrimaryItem> list)
	{
		primaryItemList = list;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <term>");
		for ( PrimaryItem p : primaryItemList )
			p.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String, Val> state) 
	{
		boolean isFloat = false;
		
		if (primaryItemList.size() < 1)
			return null;
		
		if (primaryItemList.size() == 1)
			return primaryItemList.getFirst().Eval(state);
		
		Val oneValue = primaryItemList.getFirst().Eval(state);
		if (oneValue instanceof BoolVal) 
		{
			if (primaryItemList.get(1) instanceof MulPrimaryItem)
				System.out.println("Error: * operator cannot be applied to " + oneValue);
			
			else if (primaryItemList.get(1) instanceof DivPrimaryItem)
				System.out.println("Error: / operator cannot be applied to " + oneValue);
			
			return null;
		}
		else if (!(oneValue instanceof IntVal))
			isFloat = true;

		LinkedList<Val> list = new LinkedList<Val>();
		for (int i = 1; i < primaryItemList.size(); ++i) 
		{
			Val piVal = primaryItemList.get(i).Eval(state);
			if (piVal instanceof BoolVal) 
				piVal = null;	
			list.add(piVal);
		}
		
		if (list.contains(null))
			return null;
		
		Double value = oneValue.floatVal();
		for (PrimaryItem item : primaryItemList) 
		{
			Val piVal = item.Eval(state);
			if (!(piVal instanceof IntVal))
				isFloat = true;
			
			if (item instanceof DivPrimaryItem)
				value /= piVal.floatVal();
			
			else if (item instanceof MulPrimaryItem)
				value *= piVal.floatVal();
		}
		
		if (isFloat)
			return new FloatVal(value);
		return new IntVal(value.intValue());
	}
}