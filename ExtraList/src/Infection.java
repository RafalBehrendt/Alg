import java.util.*;

public class Infection {

    Graph infectedGraph;
    int chanceOfInfecting;
    Random rand = new Random();
    HashSet<Node> listOfInfected = new HashSet<Node>();
    ArrayList<Node> visitedNodes = new ArrayList<Node>();
    ArrayList<Integer> avgInfected = new ArrayList<Integer>();
    ArrayList<Integer> avgHealthy = new ArrayList<Integer>();
    boolean healthy = false;


    int chanceForReal = 1;

    Infection(Graph graph, int chance, int numberOfStartingNodes, int levelOfDistribution) {
        infectedGraph = graph;
        if(chance < 1 || chance > 99 || numberOfStartingNodes >= graph.listOfNodes.size() || numberOfStartingNodes < 1
        || levelOfDistribution < 0 || levelOfDistribution > 3){
            throw new IllegalArgumentException("Provided probability or level of distribution is incorrect");
        }

        this.chanceOfInfecting = chance;
        chooseVictims(numberOfStartingNodes, levelOfDistribution);
    }

    void chooseVictims(int numberOfNodes, int levelOfDistribution){

        Node randomNode = infectedGraph.listOfNodes.get(rand.nextInt(infectedGraph.listOfNodes.size()));
        Queue<Node> queue = new LinkedList<Node>();

        if(levelOfDistribution == 0){ //BFS
            queue.add(randomNode);
            while(numberOfNodes > 0){
                Node nodeToInfect = queue.remove();
                nodeToInfect.changeInfo(-1);
                listOfInfected.add(nodeToInfect);
                numberOfNodes--;
                visitedNodes.add(nodeToInfect);

                    if(numberOfNodes <= 0) break;

                    for(Node childNode : nodeToInfect.connections.keySet()) {
                        if(!visitedNodes.contains(childNode)) queue.add(childNode);
                    }

                    if(queue.isEmpty()) break;
                }
        }

        if(levelOfDistribution == 1){ //DFS
            DFS(randomNode, numberOfNodes, visitedNodes);
        }

        if(levelOfDistribution ==2){
            for(int i = 0; i < numberOfNodes; i++){
                int theBiggest = 0;
                Node theBiggestNode = null;
                for(Node current : infectedGraph.listOfNodes){
                    if(current.connections.size() > theBiggest && !visitedNodes.contains(current)){
                        theBiggest = current.connections.size();
                        theBiggestNode = current;
                    }
                }
                visitedNodes.add(theBiggestNode);
                theBiggestNode.changeInfo(-1);
            }
        }

        if(levelOfDistribution == 3){
            for(int i = 0; i < numberOfNodes; i++){
                int theSmallest = 9999;
                Node theSmallestNode = null;
                for(Node current : infectedGraph.listOfNodes){
                    if(current.connections.size() < theSmallest && !visitedNodes.contains(current)){
                        theSmallest = current.connections.size();
                        theSmallestNode = current;
                    }
                }
                visitedNodes.add(theSmallestNode);
                theSmallestNode.changeInfo(-1);
            }
        }
    }

    void DFS(Node current, int limit, ArrayList<Node> visitedNodes){

        current.changeInfo(-1);
        listOfInfected.add(current);
        visitedNodes.add(current);

        if(visitedNodes.size() < limit) {
            for (Node childNode : current.connections.keySet()) {
                if (!visitedNodes.contains(childNode) && visitedNodes.size() < limit) {
                    DFS(childNode, limit, visitedNodes);
                }
            }
        }
        else return;
    }

    void infect(){
        for(Node current : infectedGraph.listOfNodes){
            if(current.infectionType == -1) discoverTrueInfo(current);
            if(!current.getInformation().equals("NONE")) {
                for(Node children : current.connections.keySet()) {
                    int roll = rand.nextInt(100) + 1;
                    if (roll <= chanceOfInfecting) {
                        children.changeInfo(current.infectionType);
                    }
                }
            }
        }
    }

    void discoverTrueInfo(Node node){
        int roll = rand.nextInt(100) + 1;
        if (roll <= chanceForReal) node.changeInfo(1);
    }

    void showStatus(){
        int numOfInfected = 0;
        int numOfHealthy = 0;
        for(int i = 0; i < infectedGraph.listOfNodes.size(); i++){
            if(infectedGraph.listOfNodes.get(i).infectionType == -1) numOfInfected++;
            if(infectedGraph.listOfNodes.get(i).infectionType == 1) numOfHealthy++;
        }

        avgInfected.add(numOfInfected);
        avgHealthy.add(numOfHealthy);

        Double nonePercent = (double)(infectedGraph.listOfNodes.size()-numOfHealthy-numOfInfected)/infectedGraph.listOfNodes.size()*100;
        System.out.println("Number of infected nodes: " + numOfInfected + " which is " +
                (double)numOfInfected/infectedGraph.listOfNodes.size()*100 + "%");
        System.out.println("Number of healthy nodes: " + numOfHealthy + " which is " +
                (double)numOfHealthy/infectedGraph.listOfNodes.size()*100 + "%");
        System.out.println("Unaffected nodes: " + (double)(infectedGraph.listOfNodes.size()-numOfHealthy-numOfInfected) + " which is " +
                 nonePercent + "%");
        //System.out.println(numOfInfected + " " + numOfHealthy);
        if(numOfHealthy == infectedGraph.listOfNodes.size()) healthy = true;
    }

}
