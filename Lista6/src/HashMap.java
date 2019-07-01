import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HashMap implements Map{

    private List<String> Keys = new ArrayList<String>();
    private List<Integer> Values = new ArrayList<Integer>();

    public String toString(){
        String result = "";
        for(int i = 0; i < Values.size(); i++){
            result += Keys.get(i) + " : " + Values.get(i) + "\n";
        }
        return result;
    }

    public int iteratorByKeys(String Key) throws NoSuchElementException {
        if(containsKey(Key)){
            for (int i = 0; i < Keys.size(); i++) {
                if (Keys.get(i) == Key) {
                    return i;
                }
            }
        }
        throw new NoSuchElementException("Brak wskazanego elementu");
    }

    public int iteratorByValues(int Value) throws NoSuchElementException {

            for (int i = 0; i < Values.size(); i++) {
                if (Values.get(i) == Value) {
                    return i;
                }
            }
        throw new NoSuchElementException("Brak wskazanej wartosci");
    }

    @Override
    public int get(String key) throws NoSuchElementException {
        if(containsKey(key)) {
            for (int i = 0; i < Keys.size(); i++) {
                if (Keys.get(i) == key) {
                    return Values.get(i);
                }
            }
        }
        throw new NoSuchElementException("Brak wskazanego elementu");
    }

    @Override
    public void put(String key, int value) {

        if(containsKey(key)){
            System.out.println("Klucz " + key + " znajduje się już w słowniku");
        }

        else {
            Values.add(value);
            Keys.add(key);
        }
    }

    @Override
    public boolean containsKey(String key) {
        if(!isEmpty()) {
            for (int i = 0; i < Keys.size(); i++) {
                if (Keys.get(i) == key) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int remove(String key) throws NoSuchElementException {
        if(containsKey(key)) {
            for (int i = 0; i < Keys.size(); i++) {
                if (Keys.get(i) == key) {
                    Values.remove(i);
                    Keys.remove(i);
                    return 1;
                }
            }
        }
        throw new NoSuchElementException("Brak wskazanego elementu");
    }

    @Override
    public void clear() {
        Keys.clear();
        Values.clear();
    }

    @Override
    public int size() {
        return Values.size();
    }

    @Override
    public boolean isEmpty() {
        if(Values.size() > 0){
            return false;
        }
        return true;
    }
}
