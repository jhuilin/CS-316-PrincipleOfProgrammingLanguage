import java.util.*;

class ArrayVar extends Var
{
	ArrayName arrayName;
	EList eList;

	ArrayVar(ArrayName aName, EList el)
	{
		arrayName = aName;
		eList = el;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		String indent2 = indent+"  ";

		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent1 + indent1.length() + " <array var>");
		arrayName.printParseTree(indent2);
		eList.printParseTree(indent2);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val v = state.get(arrayName.id.id);
		if (v != null) {	
			if ((v instanceof ArrayVal))
				return ((ArrayVal) v).getVal(eList.Eval(state));
			else {
				System.out.println("variable " + arrayName.id.id + " has a non-array value: " + v);
				return null;
			}	
		}else
			System.out.println("array variable " + arrayName.id.id + " does not have a value");
		return null;
	}

	void M(HashMap<String,Val> state, RightSide rightSide) // interpret assignment <array var> = <right side>
	{
		Val v = state.get(arrayName.id.id);
		if (v != null) {	
			if ((v instanceof ArrayVal)) 
				((ArrayVal) v).setVal(eList.Eval(state), rightSide.Eval(state));
			else{
				System.out.println("variable " + arrayName.id.id + " has a non-array value: " + v);
				return;
			}
		}else
			System.out.println("array variable " + arrayName.id.id + " does not have a value");
		return;
	}
}