import java.util.*;

class ArrayVal extends Val
{
	LinkedList<Integer> sizeList;
	Val a[]; // 1-dim array storing the array elements in row-major order

	ArrayVal(LinkedList<Integer> sl, Val a_[])
	{
		sizeList = sl;
		a = a_;
	}

	public String toString()
	{
		return "array of size list " + sizeList.toString();
	}

	Val cloneVal() // Don't clone, just return the target array itself.
	{
		return this;
	}

	double floatVal()

	// This is not used by the interpreter. For other purposes, this might return a certain code value, e.g.,
	// a hash code of the array.

	{
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
	
	private int rank(LinkedList<Integer> intList) {
		int index = intList.get(0);
		for (int i = 1; i < intList.size(); ++i) {
			index = index*sizeList.get(i);
			index = index+intList.get(i);
		}
		return index;
	}
	
	public Val getVal(LinkedList<Val> intValList) {
		if (intValList.size() == sizeList.size()){
			int index = -1;
			double floatVal = -1.0;
			LinkedList<Integer> intList = new LinkedList<Integer>();
			for (int i = 0; i < intValList.size(); ++i) {
				Val intVal = intValList.get(i);
				int size = sizeList.get(i);
				
				if (intVal != null) {
					floatVal = intVal.floatVal();
					if (floatVal == Math.round(floatVal)) {
						index = (int) floatVal;			
						if (index >= 0 && index < size)
							intList.add(index);
						else {
							System.out.println("Error: index value of array test out of range: " + index);
							return null;
						}
					}else {
						System.out.println("Error: index expression of array test evaluated to non-integer: " + intVal);
						return null;
					}
				}else
					return null;
			}
			
			if (a[rank(intList)] != null)
				return a[rank(intList)].cloneVal();
			else
				System.out.println("element of array test does not have a value");
			return null;
		}
		return null;
	}

	public void setVal(LinkedList<Val> intValList, Val rVal) {
		int index = -1;
		double floatVal = -1.0;
		if (intValList.size() == sizeList.size())	{	
			LinkedList<Integer> intList = new LinkedList<Integer>();
			for (int i = 0; i < intValList.size(); ++i) {
				Val intVal = intValList.get(i);
				int size = sizeList.get(i);
				
				if (intVal != null){
					floatVal = intVal.floatVal();
					if (floatVal == Math.round(floatVal)) {
						index = (int) floatVal;
						if (index >= 0 & index < size)
							intList.add(index);
						else {
							System.out.println("Error: index value of array test out of range: " + index);
							return;
						}
					}else{
						System.out.println("Error: index expression of array test evaluated to non-integer: " + intVal);
						return;
					}
				}else
					return;
			}
			a[rank(intList)] = rVal;
		}else
			return;
	}
}
