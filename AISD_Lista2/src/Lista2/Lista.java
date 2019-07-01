package Lista2;

abstract public class Lista implements List{

	public Pojazd [] tab = new Pojazd[0];
	int performance = 0;
	
	protected void zwiekszTablice() {
		Pojazd [] tempTab = new Pojazd[size()+1];
		for(int i = 0; i < size(); i++) {
			tempTab[i] = tab[i];
		}
		tab = tempTab;
	}
	
	protected void zmniejszTablice() {
		Pojazd [] tempTab = new Pojazd[size()-1];
		for(int i = 0; i < size()-1; i++) {
			tempTab[i] = tab[i];
		}
		tab = tempTab;
	}
	
	
	public boolean doesExist(int index) {
		if(index < 0 || index >= size()) return false;
		return true;
	}
	
	@Override
	public void add(Samochod value) {
		Pojazd tempPojazd = new Pojazd(value);
		zwiekszTablice();
		set(size()-1, tempPojazd);
	}

	@Override
	public void delete(int index) {
		if(doesExist(index)) {
			for(int i = index+1; i < size(); i++) {
				tab[i-1] = tab[i];
				performance++;
			}
			zmniejszTablice();
		}		
	}
	
	@Override
	public Pojazd get(int index) {
		if(doesExist(index)) {
			return tab[index];
		}
		return null;
	}

	@Override
	public void set(int index, Pojazd value) {
		if(doesExist(index)) {
			tab[index] = value;
		}
	}

	@Override
	public boolean isEmpty() {
		if(size() < 1) return true;
		return false;
	}

	@Override
	public int size() {
		return tab.length;
	}

	@Override
	public void clear() {
		tab = new Pojazd[0];
	}

}
