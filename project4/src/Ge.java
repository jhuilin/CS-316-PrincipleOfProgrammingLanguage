import java.util.*;

class Ge extends CompBoolPrimary
{
	Ge(E e_1, E e_2)
	{
		super(e_1, e_2);
	}
	
	String getOp()
	{
		return " >=";
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val e1Val = e1.Eval(state);
		Val e2Val = e2.Eval(state);

		if ( e1Val == null || e2Val == null )
			return null;

		if ( e1Val.isNumber() && e2Val.isNumber() )
		{
			if ( e1Val instanceof IntVal && e2Val instanceof IntVal )
				return new BoolVal(((IntVal)e1Val).val >= ((IntVal)e2Val).val);
			else
				return new BoolVal(e1Val.floatVal() >= e2Val.floatVal());
		}
		if ( e1Val instanceof BoolVal && e2Val instanceof BoolVal )
		{
			((BoolVal)e1Val).val = e1Val.floatVal() >= e2Val.floatVal();
			return e1Val;
		}

		System.out.println( "Error: >= operator cannot be applied to [" + e1Val.toString() + "," + e2Val.toString() + "]" );
		return null;
	}
}