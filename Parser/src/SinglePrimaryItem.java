
class SinglePrimaryItem extends Primary
{
	Primary primary;

	SinglePrimaryItem(Primary p)
    {
        primary = p;
    }

    void printParseTree(String indent)
    {
        primary.printParseTree(indent);
    }
}