package Lista2;

public class OrganisedArrayList extends Lista{
	private void segreguj(Pojazd pojazd){
		int i = 0;
		int perf = 0;
		boolean znaleziono = false;
		while(i < size()-1 && !znaleziono) {
			if(get(i).getSamochod().getRocznik() >= pojazd.getSamochod().getRocznik()) {
				for(int j = size()-1; j > i; j--) {
					set(j, get(j-1));
					set(j-1, pojazd);
					perf++;
				}
				performance += perf;
				znaleziono = true;
			}
			i++;
			performance++;
		}
	}
	
	@Override
	public void add(Samochod value) {
		if(value != null) {
			Pojazd tempPojazd = new Pojazd(value);
			zwiekszTablice();
			set(size()-1, tempPojazd);
			segreguj(get(size()-1));
		}
	}
	
	public void wypisz() {
		for(int i = 0; i < size(); i++) {
			System.out.println(get(i).getSamochod().getRocznik());
		}
	}

}