package lista5;

import lista5.Samochod;

public class Samochod implements Comparable<Samochod>{
	
	String marka;
	Integer rocznik;
	
	Samochod(String marka, int rocznik){
		this.marka = marka;
		this.rocznik = rocznik;
	}

	@Override
	public int compareTo(Samochod o) {	
		if(marka.compareTo(o.marka) == 0) {
			Integer tmp = rocznik;
			return tmp.compareTo(o.rocznik);
		}
		
		return marka.compareTo(o.marka);
	}
	
	@Override
	public String toString() {
		return marka + " | " + rocznik.toString();
	}

}

