import java.util.ArrayList;

public class Main {

    Graph graph = new Graph();
    ArrayList<Integer> number = new ArrayList<Integer>();

    void generateGraphAndTest(){

        graph.generateGraph(150);

//        for(int j = 1; j < 100; j++) {
//            int avg = 0;
//            for (int i = 0; i < 100; i++) {
//                graph.setInfection(j, 5, 3); //BFS
//                graph.test(1, 200);
//                avg += graph.healthyIter;
//            }
//            avg /= 100;
//            System.out.println(j + " " + avg);
//        }

        graph.setInfection(15, 5, 0); //BFS
        graph.test(1, 120);
        System.out.println();

        graph.setInfection(15, 5, 1); //DFS
        graph.test(1, 120);
        System.out.println();

        graph.setInfection(15, 5, 2); //Most connections
        graph.test(1, 120);
        System.out.println();

        graph.setInfection(15, 5, 3); // Least connections
        graph.test(1, 120);
        System.out.println();

    }


    public static void main(String args[]){

        Main main = new Main();
        main.generateGraphAndTest();

    }

}
