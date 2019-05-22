class FloatVal extends Val
{
	double val;

	FloatVal(double f)
	{
		val = f;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new FloatVal(val);
	}

	double floatVal()
	{
		return val;
	}

	boolean isNumber()
	{
		return true;
	}

	boolean isZero()
	{
		return val == 0.0;
	}
}
