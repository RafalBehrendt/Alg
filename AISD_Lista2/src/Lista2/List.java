package Lista2;

public interface List {
	public void add(Samochod obiekt);
	public void delete(int index);
	public Object get(int index);
	public void set(int index, Pojazd value);
	public boolean isEmpty();
	public int size();
	public void clear();
}
