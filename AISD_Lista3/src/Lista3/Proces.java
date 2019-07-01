package Lista3;

import java.util.Random;

public class Proces {
	
	Random generator = new Random();
	private int czasWykonania;
	private int PID;
	
	Proces(int pid){
		setCzasWykonania(generator.nextInt(100));
		setPID(pid);
	}

	public int getCzasWykonania() {
		return czasWykonania;
	}

	public void setCzasWykonania(int czasWykonania) {
		this.czasWykonania = czasWykonania;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}
	
}
