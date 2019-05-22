import java.util.*;

class Expr
{
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> btItemList)
	{
		boolTermItemList = btItemList;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <expr>");
		for ( BoolTermItem bt : boolTermItemList )
			bt.printParseTree(indent+" ");
	}

	Val Eval(HashMap<String, Val> state) 
	{
		if (boolTermItemList.size() <= 0 || boolTermItemList.getFirst() == null)
			return null;	
		
		Val value = boolTermItemList.getFirst().Eval(state);
		if (value == null)
			return null;
		
		boolean v = false;
		if (value instanceof BoolVal)
			v = ((BoolVal) value).val;
		else
		{
			if (boolTermItemList.size() == 1)
				return value;
			else
			{
				System.out.println("Error: || operator cannot be applied to 1");
				return null;
			}
		}
		for (int i = 1; i < boolTermItemList.size(); ++i) 
		{
			if (boolTermItemList.get(i) == null)
				return null;
			
			value = boolTermItemList.get(i).Eval(state);
			if (value instanceof BoolVal)
				if((v || ((BoolVal) value).val) == true)
					v = true;
				else
					v = false;
			else 
			{
				System.out.println("Error: || operator cannot be applied to 1");
				return null;
			}
		}	
		return new BoolVal(v);
	}
}