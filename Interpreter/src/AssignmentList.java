import java.util.HashMap;
import java.util.LinkedList;

class AssignmentList
{
    LinkedList<Assignment> assignmentList;

    AssignmentList(LinkedList<Assignment> al)
    {
        assignmentList = al;
    }

    void printParseTree(String indent)
    {
        for ( Assignment a : assignmentList )
            a.printParseTree(indent);
    }

    Val Eval(HashMap<String,Val> state)
    {
        for ( Assignment a : assignmentList )
            a.M(state);
        return null;
    }
}