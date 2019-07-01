package Lista2;

public class LinkedList extends Lista{
	
	Pojazd wartownik = new Pojazd(null);
	private Pojazd aktualny;
	
	LinkedList(){
		clear();
	}
	
	@Override
	public void add(Samochod pojazd){
		if(pojazd != null) {
			Pojazd tempPojazd = new Pojazd(pojazd);
			tempPojazd.next = aktualny.next;
			aktualny.next = tempPojazd;
			aktualny = tempPojazd;
			zwiekszTablice();
			set(size()-1, tempPojazd);
		}
	}

	public void delete(Pojazd pojazd) {
		boolean usunieto = false;
		if(pojazd != wartownik) {
			aktualny = wartownik;
			do {
				if(aktualny.next == pojazd) {
					aktualny.next = aktualny.next.next;
						for(int i = 0; i < size(); i++) {
							if(get(i) == pojazd) super.delete(i); 
						}
					usunieto = true;
				}
				aktualny = aktualny.next;
			}while(aktualny.next != null && !usunieto);
		}
	}
	
	public void clear() {
		tab = new Pojazd[1];
		set(0, wartownik);
		aktualny = wartownik;
	}
	
	public void next() {
		aktualny = aktualny.next;
	}
	
	public void wypisz() {
		Pojazd tempPojazd = wartownik;
		while(tempPojazd.next != null){
			System.out.println(tempPojazd.next.getSamochod().getMarka());
			tempPojazd = tempPojazd.next;
		}
	}
	
	@Override
	public boolean isEmpty() {
		aktualny = wartownik;
		if(aktualny.next == null) return true;
		return false;
	}
	
	public Pojazd getAktualny() {
		return aktualny;
	}
	
	public void setAktualny(Pojazd aktualny) {
		this.aktualny = wartownik;
		boolean done = false;
		while(this.aktualny.next != null && !done) {
			if(this.aktualny.next == aktualny) {
				this.aktualny = aktualny;
				done = true;
			}
			this.aktualny = this.aktualny.next;
		}
		
		if(!done) {
			System.out.println("Nie znaleziono podanego pojazdu!");
		}
	}
	
}
