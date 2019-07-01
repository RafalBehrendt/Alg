public interface Map {
    int get(String key) throws NoSuchElementException;
    void put(String key , int value );
    boolean containsKey(String key );
    int remove(String key) throws NoSuchElementException;;
    void clear();
    int size();
    boolean isEmpty();
}
