package lista5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
	
	final List<Samochod> unsortedList = new ArrayList<Samochod>();
	MergeSort mergeSort = new MergeSort();
	QuickSort quickSort = new QuickSort();
	HeapSort heapSort = new HeapSort();
	
	Main(int wielkosc, int poziomUporzadkawania){
		if(poziomUporzadkawania < 1 || poziomUporzadkawania > 3) throw new IllegalArgumentException("Poziom uporzadkowania miedzy 1 a 3!");
		if(wielkosc < 2) throw new IllegalArgumentException("Ilosc elementow conajmniej 2!");
		generatorTestu(wielkosc, poziomUporzadkawania);
	}
	
	public void generatorTestu(int wielkosc, int poziomUporzadkowania) {
		
		unsortedList.clear();
		Random rand = new Random();
		String [] cars = new String[] {"Audi", "BMW", "Citroen", "Honda", "Mazda", "Opel", "Peugeot", "Renault", "Skoda", "Volvo"};
		
			for(int i = 0; i < wielkosc; i++) {
				int whichCar = rand.nextInt(3*poziomUporzadkowania+1);
				int whichYear = rand.nextInt(75*poziomUporzadkowania) + 2001;
				unsortedList.add(new Samochod(cars[whichCar], whichYear));
			}
	}

	public void test(ListSorter sort, int iloscTestow) {
		
		long srednia = 0;
		
		for(int i = 0; i < iloscTestow; i++) {
			sort.sort(unsortedList);
			//System.out.println("Sredni czas wykonania algorytmu: " + sort.getDuration() + " nanosekund.");
			srednia += sort.getDuration();
		}
		
		System.out.println("\n");
		
		srednia /= iloscTestow;
		
		System.out.println("Sredni czas wykonania algorytmu: " + srednia + " nanosekund.");
		//System.out.println(srednia);
		//System.out.println("\n");
	}
	
	public String toString() {
		String wynik = "";
		for(int i = 0; i < unsortedList.size(); i++) {
			wynik += unsortedList.get(i) + "\n";
		}
		return wynik;
	}
	
	
	public static void main(String[] args) {
		
		Main main = new Main(100, 1);
		
		main.test(main.mergeSort, 10);
		main.test(main.quickSort, 10);
		main.test(main.heapSort, 10);
		
		//System.out.println(main.heapSort.toString());
		
		
	}
	
	}
