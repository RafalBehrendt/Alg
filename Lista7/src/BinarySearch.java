import java.util.*;

public class BinarySearch {

    List<Integer> list = new ArrayList<Integer>();
    Random rand = new Random();

    public int binarySearch(List<Integer> list, int what, int right){
        int left=0;
        int middle;
        int loops = 0;
        while(left<=right){
            loops++;
            middle=(left+right)/2; // klasyczna wartosc middle
//            int bound = right - left;
//            middle=rand.nextInt(bound+1) + left; // wartosc losowa, która być może lepiej zadziała w przypadku, gdy
                                                   // szukana liczba znajduje się na jednym z krańców
            int compValue = list.get(middle) - what;
            if(compValue==0) {
                return loops;
            }
            if(compValue>0)
                right=middle-1;
            else
                left=middle+1;
        }
        return loops;
    }

    void test(int loops){

        for(int j = loops; j < list.size(); j+=loops) {
            int linearLoops = 0;
            int search = rand.nextInt(2*j);
            for(int i = 0; i < j; i++){
                linearLoops++;
                if(list.get(i) == search){
                    i = j;
                }
            }
            int bs = binarySearch(list,search,j);
            long wynik = 0;

            for(int i = 0; i <= bs; i++){
                wynik += Math.pow(2,i);
            }

            System.out.println(bs + " " + linearLoops + " " + wynik);
        }

    }

    BinarySearch(){
        for(int i = 0; i < 100000; i++){
            list.add(i);
        }
    }

    public static void main(String[] args){

        BinarySearch main = new BinarySearch();
        main.test(1000);

    }

}
