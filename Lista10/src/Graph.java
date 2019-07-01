
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Graph {

    HashSet<Node> listOfNodes = new HashSet<Node>();
    Random rand = new Random();

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
            listOfNodes.add(new Node(ID));
    }

    void addNewConnection(int parentID, int childID, int weigth){
        Node parent = getNode(parentID);
        Node child = getNode(childID);

        if(parent != null && child != null && parent != child){
            parent.connections.put(child, weigth);
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

    void Dijkstra(int IDOfBeginning){

        Node beginning = getNode(IDOfBeginning);
        HashMap<Node, Double> listToVisit = new HashMap<Node, Double> ();

        if(beginning != null) {
            for (Node currentNode : listOfNodes) {
                if (currentNode != beginning) listToVisit.put(currentNode, Double.POSITIVE_INFINITY);
            }

            dkstr(beginning, listToVisit, (double) 0);

            for (Node currentNode : listToVisit.keySet()) {
                System.out.print(currentNode.ID + " ");
            }

            System.out.println();

            for (Node currentNode : listToVisit.keySet()) {
                DecimalFormat df = new DecimalFormat("###.#");
                if (listToVisit.get(currentNode) == Double.POSITIVE_INFINITY) System.out.print("∞ ");
                else System.out.print(df.format(listToVisit.get(currentNode)) + " ");
            }
        }
        else{
            System.out.println("Given vertex does not exist");
        }

    }

    void dkstr(Node beginning, HashMap<Node, Double> listToVisit, Double restOfRoute){

        for(Node currentNode : listToVisit.keySet()){
            if(beginning.connections.containsKey(currentNode)){
                if(beginning.connections.get(currentNode) + restOfRoute < listToVisit.get(currentNode)){
                    listToVisit.put(currentNode, Double.valueOf(beginning.connections.get(currentNode) + restOfRoute));
                }
            }
        }

        double theSmallest = Double.POSITIVE_INFINITY;
        Node theSmallestNode = null;

        for(Node currentNode : listToVisit.keySet()) {
            if(listToVisit.get(currentNode) < theSmallest)
                if(!currentNode.visited) {
                    theSmallestNode = currentNode;
                    theSmallest = listToVisit.get(currentNode);
                }
        }

        if (theSmallestNode == null) return;

        else {

            theSmallestNode.visited = true;
            dkstr(theSmallestNode, listToVisit, theSmallest);
        }

    }


    void Kruskal(){

        class connection{
            Node parentNode = null;
            Node childNode = null;
            int weight = 0;

            connection(Node parentNode, Node childNode, int weight){
                this.parentNode = parentNode;
                this.childNode = childNode;
                this.weight = weight;
            }
        }

        ArrayList<connection> sortedConnections = new ArrayList<connection>();
        Graph kruskalsGraph = new Graph(this);

        long tStart = System.nanoTime();

            for (Node currentNode : listOfNodes) {
                for (Node innerNode : currentNode.connections.keySet()) {
                    sortedConnections.add(new connection(currentNode, innerNode, currentNode.connections.get(innerNode)));
                }
            }

            for(int i = 0; i < sortedConnections.size(); i++){
                for(int j = 0; j < i; j++){
                    if(sortedConnections.get(i).weight <= sortedConnections.get(j).weight){
                        connection buffer = sortedConnections.get(j);
                        sortedConnections.set(j, sortedConnections.get(i));
                        sortedConnections.set(i, buffer);
                    }
                }
            }

        for(int i = 0; i < sortedConnections.size(); i++){
            //System.out.println(sortedConnections.get(i).weight);
            connection tmpConnection = sortedConnections.get(i);
            if(!tmpConnection.parentNode.family.contains(tmpConnection.childNode)){

                for(int k = 0; k < tmpConnection.childNode.family.size(); k++){
                    //System.out.println(tmpConnection.childNode.family.get(k).ID);
                    if(!tmpConnection.parentNode.family.contains(tmpConnection.childNode.family.get(k))){
                        tmpConnection.parentNode.family.add(tmpConnection.childNode.family.get(k));
                    }
                }

                for(int l = 0; l < sortedConnections.get(i).childNode.family.size(); l++){
                    if(tmpConnection.childNode.family.get(l) != tmpConnection.childNode){
                        tmpConnection.childNode.family.get(l).family.clear();
                        tmpConnection.childNode.family.get(l).family.addAll(tmpConnection.parentNode.family);
                    }
                }

                tmpConnection.childNode.family.clear();
                tmpConnection.childNode.family.addAll(tmpConnection.parentNode.family);

                kruskalsGraph.addNewConnection(tmpConnection.parentNode.ID, tmpConnection.childNode.ID, tmpConnection.weight);
            }
        }

        kruskalsGraph.AdjacencyListWeighted();

        long tEnd = System.nanoTime();
//        System.out.println(tEnd - tStart);
    }



    public static void main(String[] args){

        Graph graph = new Graph();

        graph.generateGraph(7);

//        graph.addNewElement(1);
//        graph.addNewElement(2);
//        graph.addNewElement(3);
//        graph.addNewElement(4);
//
//        graph.addNewConnection(1, 2, 1);
//        graph.addNewConnection(2, 1, 2);
//        graph.addNewConnection(2, 3, 2);
//        graph.addNewConnection(3, 4, 3);
//        graph.addNewConnection(4, 2, 2);


        System.out.println("Adjacency lists:\n ");
//
        System.out.println("Directed Graph: ");
        graph.AdjacencyListDirected();
        System.out.println("\nWeighted Graph: ");
        graph.AdjacencyListWeighted();
//
        System.out.println("\nAdjacency matrixes:\n ");
        System.out.println("Directed Graph: ");
        graph.AdjacencyMatrixDirected();
        System.out.println("\nWeighted Graph: ");
        graph.AdjacencyMatrixWeighted();
//
        System.out.println("\nDijkstra's shortest path from node 1 to each node: \n");

        graph.Dijkstra(1);

        System.out.println("\nKruskal's reducted graph: \n");

//        for(int i = 50; i <= 10000; i+=50) {
//            graph.generateGraph(i);
//            System.out.print(i + " ");
//            graph.Kruskal();
//        }

        graph.Kruskal();

    }

}
