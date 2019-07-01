package Lista2;

public class Pojazd{
	
	public Pojazd next;
	private Samochod samochod;
	
	Pojazd(Samochod samochod){
		this.samochod = samochod;
		next = null;
	}

	public Samochod getSamochod() {
		return samochod;
	}

	public void setSamochod(Samochod samochod) {
		this.samochod = samochod;
	}

}