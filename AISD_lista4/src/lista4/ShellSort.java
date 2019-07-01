package lista4;

import java.util.ArrayList;
import java.util.List;

public class ShellSort implements ListSorter{
	
	List<Integer> increasion;
	List<Samochod> sortedList;
	InsertSort insertSort = new InsertSort();
	private long duration;
	
	ShellSort(List<Integer> increasion){
		
		if(increasion.size() > 1) {
			if(increasion.get(increasion.size()-1) != 1) {
				throw new IllegalArgumentException("Ciag przyrostow musi byc zakonczony jedynk¹!");
			}
			else this.increasion = increasion;
		}
		else throw new IllegalArgumentException("Ci¹g przyrostow musi zawierac conajmniej dwa elementy!");
		
	}
	
	@Override
	public List<Samochod> sort(final List<Samochod> list) {
		long startTime = System.nanoTime();
		sortedList = new ArrayList<Samochod>(list);
		for(int i = 0; i < increasion.size()-1; i++) {
			for(int j = 0; j < increasion.get(i); j++) {
				int threshold = 0;
				List<Samochod> tmpList = new ArrayList<Samochod>();
				
				while(threshold + j < sortedList.size()) {
					tmpList.add(sortedList.get(j+threshold));
					threshold += increasion.get(i);
				}
				
				tmpList = insertSort.sort(tmpList);
				threshold = 0;
				
				for(int k = 0; k < tmpList.size(); k++) {
					sortedList.set(j+threshold, tmpList.get(k));
					threshold += increasion.get(i);
				}
				
				tmpList.clear();
			}
		}
		sortedList = insertSort.sort(sortedList);
		long endTime = System.nanoTime();
		sortedList.clear();
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
