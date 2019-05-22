// Value objects used for expression evaluation and returned by Eval function.

abstract class Val
{
	abstract Val cloneVal();
	abstract double floatVal(); // conversion to double-type floating-point number
	abstract boolean isNumber();
	abstract boolean isZero();
}
