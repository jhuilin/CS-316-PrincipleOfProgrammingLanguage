import java.util.*;

class Neq extends CompBoolPrimary
{
	Neq(E a, E b)
	{
		super(a, b);
	}
	
	String getOp()
	{
		return " !=";
	}

	@Override
	BoolVal Eval(HashMap<String, Val> state) 
	{
		Val l = e1.Eval(state);
		Val r = e2.Eval(state);
		
		if (l != null || r != null)
		{
			if (l instanceof BoolVal && r instanceof BoolVal)
				return new BoolVal(l.floatVal() != r.floatVal());
			
			else if (l.isNumber() && r.isNumber()) 
				return new BoolVal(l.floatVal() != r.floatVal());	
			System.out.println("Error: != operator cannot be applied to [" + l +  "," + r + "]");
		}
		return null;
	}
}