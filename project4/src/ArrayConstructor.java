import java.util.*;

class ArrayConstructor extends RightSide
{
	EList eList;

	ArrayConstructor(EList el)
	{
		eList = el;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <array constructor>");
		eList.printParseTree(indent1+" ");
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		LinkedList<Val> allVals = new LinkedList<Val>();
		allVals = eList.Eval(state);
		
		LinkedList<Integer> capacitys = new LinkedList<Integer>();
		int total = 1;
		double floatVal = -1.0;
		
		for (Val v : allVals) {
			if (v != null) {			
				floatVal = v.floatVal();
				if (floatVal == Math.round(floatVal)) {	
					if (v.floatVal() >= 0.0) {
						capacitys.add(((int) v.floatVal()));
						total = total*((int) v.floatVal());		
					}else {
						System.out.println("Error: array size expression evaluated to non-positive integer: " + v);
						return null;
					}
				}else {
					System.out.println("Error: array size expression evaluated to non-integer: " + floatVal);
					return null;
				}
			}else
				return null;
		}
		return new ArrayVal(capacitys, new Val[total]);
	}
}
