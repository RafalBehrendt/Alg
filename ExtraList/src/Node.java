import java.util.HashMap;

public class Node {

    HashMap<Node, Integer> connections = new HashMap<Node, Integer>();
    int ID;
    private String information = "NONE";
    int infectionType = 0;

    Node(int ID){
        this.ID = ID;
    }

    void changeInfo(int kind){
        if(kind == -1) {
            information = "INFECTED";
            infectionType = -1;
        }
        if(kind == 1) {
            information = "HEALTHY";
            infectionType = 1;
        }
        if(kind == 0){
            information = "NONE";
            infectionType = 0;
        }
    }

    void clearInfo(){
        information = "NONE";
        infectionType = 0;
    }

    String getInformation(){
        return this.information;
    }

}
