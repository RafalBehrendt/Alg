package Lista2;


public class OrganisedLinkedList extends Lista{
	
	Pojazd aktualny;
	Pojazd head = new Pojazd(null);
	Pojazd tail = new Pojazd(null);
	
	OrganisedLinkedList(){
		clear();
	}

	public Pojazd segreguj(Pojazd pojazd) {
		aktualny = head;
		boolean posegregowano = false;
		while(aktualny.next != tail && !posegregowano) {
			if(aktualny.next.getSamochod().getRocznik() >= pojazd.getSamochod().getRocznik()) {
				pojazd.next = aktualny.next;
				aktualny.next = pojazd;
				posegregowano = true;
			}
			aktualny = aktualny.next;
			performance++;
		}
		
		if(!posegregowano) {
			pojazd.next = aktualny.next;
			aktualny.next = pojazd;
		}
		
		return pojazd;
	}
	
	public void add(Samochod pojazd){
		if(pojazd != null) {
			Pojazd tempPojazd = new Pojazd(pojazd);
			tempPojazd = segreguj(tempPojazd);
			zwiekszTablice();
			set(size()-1, tempPojazd);
		}
	}
	
	public void delete(Pojazd pojazd) {
		boolean usunieto = false;
		if(pojazd != tail && pojazd != head) {
			aktualny = head;
			int perf = 0;
			do {
				if(aktualny.next == pojazd) {
					aktualny.next = aktualny.next.next;
					/* usuniêcie elementu z tablicy - je¿eli nie uzywamy to wiêksza wydajnoœc, lecz wiêksze wykorzystanie pamiêci, przy 
					 * du¿ej ilosci usuwanych elementów
						for(int i = 0; i < size(); i++) {
							if(get(i) == pojazd) super.delete(i);
							perf++;
						}
						*/
					usunieto = true;
				//	performance += perf;
				}
				aktualny = aktualny.next;
				performance++;
			}while(aktualny.next != tail && !usunieto);
		}
	}
	
	public void wypisz() {
		aktualny = head;
		while(aktualny.next != tail) {
			System.out.println(aktualny.next.getSamochod().getRocznik());
			aktualny = aktualny.next;
		}
	}
	
	public void next() {
		aktualny = aktualny.next;
	}
	
	public void setAktualny(Pojazd aktualny) {
		this.aktualny = head;
		boolean done = false;
		while(this.aktualny.next != null && !done) {
			if(this.aktualny.next == aktualny) {
				this.aktualny = aktualny;
				done = true;
			}
			this.aktualny = this.aktualny.next;
		}
		
	}
	
	public void clear() {
		tab = new Pojazd[2];
		set(1, tail);
		set(0, head);
		head.next = tail;
		tail.next = head;
		aktualny = head;
	}

}

