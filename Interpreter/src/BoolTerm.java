import java.util.*;

class BoolTerm
{
	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> list)
	{
		boolPrimaryItemList = list;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <boolTerm>");
		for ( BoolPrimaryItem item : boolPrimaryItemList )
			item.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String, Val> state) 
	{
		if (boolPrimaryItemList.size() <= 0 || boolPrimaryItemList == null || boolPrimaryItemList.getFirst() == null)
			return null;	
		Val v = boolPrimaryItemList.getFirst().Eval(state);
		
		boolean values;
		if (v instanceof BoolVal)
			values = ((BoolVal) v).val;
		else 
		{
			if (boolPrimaryItemList.size() == 1)
				return v;
			else
			{
				System.out.println("Error: && operator cannot be applied to 1");
				return null;
			}
		}
		
		for (int i = 1; i < boolPrimaryItemList.size(); ++i) 
		{
			if (boolPrimaryItemList.get(i) == null)
				return null;
			
			v = boolPrimaryItemList.get(i).Eval(state);		
			if (v instanceof BoolVal)
			{
				if (values && ((BoolVal) v).val == true)
					values = true;
				else
					values = false;
			}
			else
			{
				System.out.println("Error: && operator cannot be applied to 1");
				return null;
			}
		}	
		return new BoolVal(values);
	}
}

