import java.util.LinkedList;

class SList 
{
	LinkedList<Statement> statements;
	
	public SList(LinkedList<Statement> s) 
	{
		statements = s;
	}

	public void printParseTree(String indent) 
	{
		IO.displayln(indent + indent.length() + " <s list>");
		String indent1 = indent + " ";
		for(Statement s : statements) {
			s.printParseTree(indent1);
		}
	}
}