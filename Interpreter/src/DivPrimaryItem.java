import java.util.*;

class DivPrimaryItem extends PrimaryItem


{
	DivPrimaryItem(Primary p)
	{
		primary = p;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " /");
		primary.printParseTree(indent);
	}
	
	@Override
	Val Eval(HashMap<String, Val> state) 
	{
		if (primary.Eval(state) instanceof BoolVal)
		{
			System.out.println("Error: / operator cannot be applied to " + primary.Eval(state));
			return null;
		}
		if (primary.Eval(state).floatVal() == 0)
		{
            System.out.println("Error: division by 0");
			return null;
		}
		return primary.Eval(state);
	}
}