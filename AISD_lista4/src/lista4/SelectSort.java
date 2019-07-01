package lista4;

import java.util.ArrayList;
import java.util.List;

public class SelectSort extends Sort implements ListSorter{
	
	List<Samochod> sortedList;
	private long duration;
	
	private void swap(List<Samochod> list, int left, int right) {
		if (left != right) {
			 Samochod temp = list.get(left);
			 list.set(left, list.get(right));
			 list.set(right, temp);
		}
	}
	
	public List<Samochod> sort(final List<Samochod> list) {
		long startTime = System.nanoTime();
		sortedList = new ArrayList<Samochod>(list);
		for(int i = 0; i < sortedList.size(); i++) {
			int smallest = i;
			for (int j = smallest+1; j < sortedList.size(); j++) {
				if(sortedList.get(j).compareTo(sortedList.get(smallest)) < 0) smallest = j;
			}
			swap(sortedList, i, smallest);
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
		long duration = this.duration;
		this.duration = 0;
		return duration;
	}

	private void setDuration(long duration) {
		this.duration = duration;
	}


}
