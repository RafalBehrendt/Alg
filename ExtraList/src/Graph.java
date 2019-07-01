import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Graph {

    ArrayList<Node> listOfNodes = new ArrayList<Node>();
    private Random rand = new Random();
    private Infection infection;
    int healthyIter = 0;

    Graph(){ }

    Graph(Graph givenGraph){
        for(Node currentNode : givenGraph.listOfNodes){
            addNewElement(currentNode.ID);
        }
    }

    private boolean doesContain(int ID){
        for(Node currentNode : listOfNodes){
            if(currentNode.ID == ID) return true;
        }
        return false;
    }

    private Node getNode(int ID){
        for(Node currentNode : listOfNodes){
            if(currentNode.ID == ID) return currentNode;
        }
        return null;
    }

    void addNewElement(int ID){
        Node newNode = new Node(ID);
        listOfNodes.add(newNode);
        addNewConnection(newNode.ID, rand.nextInt(listOfNodes.size()), rand.nextInt(20));
    }

    void addNewConnection(int parentID, int childID, int weigth){
        Node parent = getNode(parentID);
        Node child = getNode(childID);

        if(parent != null && child != null && parent != child){
            parent.connections.put(child, weigth);
            child.connections.put(parent, weigth);
        }

    }

    void deleteNode(int ID){

        Node nodeToDel;

        nodeToDel = getNode(ID);
        listOfNodes.remove(nodeToDel);

        for(Node currentNode : listOfNodes){
            for(Node innerNode : currentNode.connections.keySet()){
                innerNode.connections.remove(nodeToDel);
            }
        }

    }

    void AdjacencyListDirected(){
        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID);
            for(Node innerNode : currentNode.connections.keySet()){
                System.out.print( " -> " + innerNode.ID);
            }
            System.out.println();
        }
    }

    void AdjacencyListWeighted(){
        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID);
            for(Node innerNode : currentNode.connections.keySet()){
                System.out.print(" -> [ " +innerNode.ID + " | " + currentNode.connections.get(innerNode) + " ]");
            }
            System.out.println();
        }
    }

    void AdjacencyMatrixDirected(){
        System.out.print("  ");
        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID + " ");
        }

        System.out.println();

        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID + " ");
            for(Node innerNode : listOfNodes){
                if(!currentNode.connections.containsKey(innerNode)) System.out.print(0 + " ");
                else System.out.print(1 + " ");
            }
            System.out.println();
        }
    }

    void AdjacencyMatrixWeighted(){
        System.out.print("  ");
        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID + " ");
        }

        System.out.println();

        for(Node currentNode : listOfNodes){
            System.out.print(currentNode.ID + " ");
            for(Node innerNode : listOfNodes){
                if(!currentNode.connections.containsKey(innerNode)) System.out.print("∞ ");
                else System.out.print(currentNode.connections.get(innerNode) + " ");
            }
            System.out.println();
        }
    }

    public void showStatus() {
        for(int i = 0; i < listOfNodes.size(); i++){
            System.out.println(listOfNodes.get(i).getInformation());
        }
    }

    void generateGraph(int numberOfElements){

        listOfNodes.clear();

        for(int i = 0; i < numberOfElements; i++){
            addNewElement(i+1);
        }

        for(int i = 0; i < numberOfElements*2; i++){
            addNewConnection(rand.nextInt(numberOfElements)+1,
                    rand.nextInt(numberOfElements)+ 1, rand.nextInt(9)+1);
        }
    }

    void setInfection(int chance, int numberOfStartingNodes, int levelOfDistribution){
        infection = new Infection(this, chance, numberOfStartingNodes, levelOfDistribution);
        emptyInfo();
    }

    void emptyInfo(){
        for(int i = 0; i < listOfNodes.size(); i++){
            if(!infection.visitedNodes.contains(listOfNodes.get(i))) listOfNodes.get(i).clearInfo();
            else listOfNodes.get(i).changeInfo(-1);
        }
    }

    void test(int numOfTests, int numOfIterations){
        healthyIter = 0;
        for(int i = 0; i < numOfTests; i++){
            emptyInfo();
            //System.out.print("0 ");
            infection.showStatus();
            for(int j = 0; j<numOfIterations; j++){
                //System.out.print(j+1 + " ");
                infection.infect();
//                if(infection.healthy && healthyIter == 0) healthyIter = j;
            }
            infection.showStatus();

            //System.out.println(healthyIter);
        }

//        int averageResultInf = 0;
//        int averageResultHeal = 0;
//
//
//        for(int j = 0; j < numOfIterations; j++){
//            averageResultInf = 0;
//            averageResultHeal = 0;
//            for(int i = j; i < infection.avgInfected.size(); i+=infection.avgInfected.size()/numOfTests){
//                averageResultInf = averageResultInf + infection.avgInfected.get(i);
//                averageResultHeal = averageResultHeal + infection.avgHealthy.get(i);
//            }
//            System.out.println(j+1 + " " + averageResultInf/numOfTests + " " + averageResultHeal/numOfTests);
//        }

    }


}
