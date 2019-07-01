package lista4;

import java.util.ArrayList;
import java.util.List;

public class InsertSort implements ListSorter{
	
	List<Samochod> sortedList;
	private long duration;

	@Override
	public List<Samochod> sort(final List<Samochod> list) {
		sortedList = new ArrayList<Samochod>(list);
		long startTime = System.nanoTime();
		
		for(int i = 1; i < sortedList.size(); i++) {
			Samochod value = sortedList.get(i);
			int j = i;
			for(j = i; j > 0 && value.compareTo(sortedList.get(j-1)) < 0; j--) {
				sortedList.set(j, sortedList.get(j-1));
			}
			sortedList.set(j, value);
		}
		
		long endTime = System.nanoTime();
		setDuration((endTime - startTime));
		return sortedList;
	}
	
	@Override
	public String toString() {
		String wynik = "";
		for(int i = 0; i < sortedList.size(); i++) {
			wynik = wynik + sortedList.get(i).toString() + "\n";	
		}
		return wynik;
	}

	@Override
	public long getDuration() {

		return duration;
	}

	private void setDuration(long duration) {
		this.duration = duration;
	}
	
}
