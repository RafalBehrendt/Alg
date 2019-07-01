package lista5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort implements ListSorter{
	
	Random rnd = new Random();
	long duration = 0;
	List<Samochod> sortedList;
	
	public List<Samochod> sort(List<Samochod> list) {
		long startTime = System.nanoTime();
		sortedList = new ArrayList<Samochod>(list);
		quicksort(sortedList, 0, sortedList.size()-1);
		long endTime = System.nanoTime();
		duration = endTime - startTime;
		return sortedList;
		 
	}
	
    public void quicksort(List<Samochod> list, int startIdx, int endIdx){

    if(endIdx > startIdx) {
    	int random = rnd.nextInt(endIdx-startIdx) + startIdx;
        Samochod pivot = list.get(random);
        swap(list, random, startIdx);
        int i = startIdx+1;

        for(int j = startIdx+1; j<= endIdx; j++){
            if(pivot.compareTo(list.get(j)) > 0){
            	swap(list, j, i);
                i++; 
            }
        }
        
        swap(list, i-1, startIdx);

        quicksort(list, startIdx, i-2); 
        quicksort(list, i, endIdx); 
    }
}
		
		private void swap(List<Samochod> list, int left, int right) {
		
			if (left != right) {
				Samochod temp = list.get(left);
				list.set(left, list.get(right));
				list.set(right, temp);
			}
		}
		
		@Override
		public long getDuration() {
			long wynik = duration;
			duration = 0;
			return wynik;
		}
		
		public String toString() {
			String wynik = "";
			for(int i = 0; i < sortedList.size(); i++) {
				wynik += sortedList.get(i).toString() + "\n";
			}
			return wynik;
		}
}
