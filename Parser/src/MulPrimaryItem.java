
class MulPrimaryItem extends Primary
{
	Primary primary;

	MulPrimaryItem(Primary p)
    {
        primary = p;
    }

	void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " *");
        primary.printParseTree(indent);
    }
}