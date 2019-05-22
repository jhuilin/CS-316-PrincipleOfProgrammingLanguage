
class DivPrimaryItem extends Primary
{
	Primary primary;

	DivPrimaryItem(Primary p)
    {
        primary = p;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " /");
        primary.printParseTree(indent);
    }
}