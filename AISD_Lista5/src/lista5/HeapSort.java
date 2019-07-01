package lista5;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements ListSorter{
	
	List<Samochod> sortedList;
	long duration = 0;

	@Override
	public List<Samochod> sort(List<Samochod> list){
		sortedList = new ArrayList<Samochod>(list);
		long startTime = System.nanoTime();
		heapsort(sortedList, list.size());
		long endTime = System.nanoTime();
		duration = endTime - startTime;
		return sortedList;
	 }
	
	private void heapsort(List<Samochod> heap, int n) {
		 heapAdjustment(heap, n);
		 
		 for(int i = n-1; i > 0; i--){
			 swap(heap,i,0);
			 heapify(heap,0,i);
		 }
	}
	
	private void swap(List<Samochod> list, int left, int right) {
		if (left != right) {
			 Samochod temp = list.get(left);
			 list.set(left, list.get(right));
			 list.set(right, temp);
		}
	}
	

	public void heapify(List<Samochod> heap,int idx, int n){
		 int idxOfBigger=2*idx+1;
		 if(idxOfBigger<n){
			 if(idxOfBigger+1<n && heap.get(idxOfBigger).compareTo(heap.get(idxOfBigger+1))<0) idxOfBigger++;
			 if(heap.get(idx).compareTo(heap.get(idxOfBigger))<0){ 
				 swap(heap,idx,idxOfBigger);
				 heapify(heap,idxOfBigger,n); 
			 }
		 }
		}
		
	
	void heapAdjustment(List<Samochod> heap,int n)
		{	
			 for(int i=(n-1)/2; i>=0; i--)
			 heapify(heap, i, n);
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
			wynik += sortedList.get(i) + "\n";
		}
		return wynik;
	}



}
