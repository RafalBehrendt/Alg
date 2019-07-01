import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Node {

    HashMap<Node, Integer> connections = new HashMap<Node, Integer>();
    int ID;
    boolean visited = false;
    ArrayList<Node> family = new ArrayList<Node>();

    Node(int ID){
        this.ID = ID;
        family.add(this);
    }

}
