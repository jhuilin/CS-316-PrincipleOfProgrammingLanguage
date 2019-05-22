
class VarPrimary extends Primary
{
	Var var;
	
	VarPrimary(Var v) 
	{
		var = v;
	}
	
	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <primary>");
		String indent1 = indent + " ";
		IO.displayln(indent1 + indent1.length() + " <var primary>");
		var.printParseTree(indent1+" ");
	}
}