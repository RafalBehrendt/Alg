package lista5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergeSort implements ListSorter{
	
	long duration = 0;
	List<Samochod> sortedList;

	@Override
	public List<Samochod> sort(List<Samochod> list) {
		List<Samochod> tmpList = new ArrayList<Samochod>(list);
		long startTime = System.nanoTime();
		sortedList = mergesort(tmpList, 0, tmpList.size()-1);
		long endTime = System.nanoTime();
		duration = endTime - startTime;
		return sortedList;
	}
	
	private List<Samochod> mergesort(List<Samochod> list, int startIndex, int endIndex) {
		
	 if (startIndex == endIndex) {
		 List<Samochod> result = new ArrayList<Samochod>();
		 result.add(list.get(startIndex));
		 return result;
	 }
	 
	 int splitIndex = startIndex + (endIndex - startIndex) / 2;
	 return merge(mergesort(list, startIndex, splitIndex),
	 mergesort(list, splitIndex + 1, endIndex));
	 
	}


	private List<Samochod> merge(List<Samochod> left, List<Samochod> right) {

	 List<Samochod> result = new ArrayList<Samochod>();
 
	 int indexOfRight = 0;
	 int indexOfLeft = 0;
	 
	 while(indexOfLeft < left.size() && indexOfRight < right.size()) {
		 if(left.get(indexOfLeft).compareTo(right.get(indexOfRight)) < 0) {
			 result.add(left.get(indexOfLeft++));
		 }
		 else {
			 result.add(right.get(indexOfRight++));
		 }
	 }
	
	 for(int i = indexOfLeft; i < left.size(); i++) {
	 	result.add(left.get(i));
	 }
	
	 for(int i = indexOfRight; i < right.size(); i++) {
		 result.add(right.get(i));
	 }
	 
	 return result;
	 
	}
	
	@Override
	public long getDuration() {
		long result = duration;
		duration = 0;
		return result;
	}
	
	@Override
	public String toString(){
		String wynik = "";
		
		for(int i = 0; i < sortedList.size(); i++) {
			wynik += sortedList.get(i).toString() + "\n";
		}
		
		return wynik;
	}


}
