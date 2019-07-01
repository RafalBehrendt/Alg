import java.util.ArrayList;
import java.util.List;

public class HashSet implements Set {

    private List<String> Set = new ArrayList<String>();

    public String toString(){
        String value = "";
        for(int i = 0; i < size(); i++){
            value += Set.get(i) + "\n";
        }
        return value;
    }

    @Override
    public void add(String value) {
        if(contains(value)){
            System.out.println("Klucz " + value + " znajduje się już w zbiorze");
        }
        else {
            Set.add(value);
        }
    }

    @Override
    public boolean contains(String value) {
        for(int i = 0; i < Set.size(); i++){
            if(Set.get(i).equals(value)) return true;
        }
        return false;
    }

    @Override
    public void remove(String value) {
            for(int i = 0; i < Set.size(); i++) {
                if (Set.get(i).equals(value)) {
                    Set.remove(i);
                }
            }
    }

    public String get(int index){
        return Set.get(index);
    }

    @Override
    public void clear() {
        Set.clear();
    }

    @Override
    public int size() {
        return Set.size();
    }

    @Override
    public boolean isEmpty() {
        if(size() < 1) return true;
        return false;
    }
}
