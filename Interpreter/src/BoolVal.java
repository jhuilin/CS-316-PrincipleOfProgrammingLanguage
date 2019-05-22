class BoolVal extends Val
{
	boolean val;

	BoolVal(boolean b)
	{
		val = b;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new BoolVal(val);
	}

	double floatVal()
	{
		if ( val )
			return 1.0;
		else
			return 0.0;
	}

	boolean isNumber()
	{
		return false;
	}

	boolean isZero()
	{
		return false;
	}
}