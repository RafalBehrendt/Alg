package lista4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
	
	final List<Samochod> unsortedList = new ArrayList<Samochod>();
	InsertSort insertSort = new InsertSort();
	SelectSort selectSort = new SelectSort();
	BubbleSort bubbleSort = new BubbleSort();
	List<Integer> increasions = Arrays.asList(5,3,1);
	ShellSort shellSort = new ShellSort(increasions);
	
	Main(int wielkosc, int poziomUporzadkawania){
		if(poziomUporzadkawania < 1 || poziomUporzadkawania > 3) throw new IllegalArgumentException("Poziom uporzadkowania miedzy 1 a 3!");
		if(wielkosc < 2) throw new IllegalArgumentException("Ilosc elementow conajmniej 2!");
		generatorTestu(wielkosc, poziomUporzadkawania);
	}
	
	public void generatorTestu(int wielkosc, int poziomUporzadkowania) {
		
		unsortedList.clear();
		Random rand = new Random();
		String [] cars = new String[] {"Audi", "BMW", "Citroen", "Honda", "Mazda", "Opel", "Peugeot", "Renault", "Skoda", "Volvo"};
		
		for(int poziom = 0; poziom < poziomUporzadkowania; poziom++) {
			for(int i = 0; i < wielkosc; i++) {
				int whichCar = rand.nextInt(poziomUporzadkowania * 3 + 1);
				int whichYear = rand.nextInt(poziomUporzadkowania * 3 + 1) + 2001;
				unsortedList.add(new Samochod(cars[whichCar], whichYear));
			}
		}
	}
	
	public void test(ListSorter sort, int iloscTestow) {
		
		for(int i = 0; i < iloscTestow; i++) {
			sort.sort(unsortedList);
			System.out.println("Czas wykonania algorytmu: " + sort.getDuration() + " nanosekund.");
		}
	}
	
	@Override
	public String toString() {
		String wynik = "";
		for(int i = 0; i < unsortedList.size(); i++) {
			wynik = wynik + unsortedList.get(i).toString() + "\n";	
		}
		return wynik;
	}
	
	public static void main(String[] args) {
		
		Main main = new Main(1000000, 1);
		
		//System.out.println(main.toString()); // wypisanie wygenerowanej tablicy
		
		main.test(main.insertSort, 3);
		System.out.println("\n");
		
		main.test(main.selectSort, 3);
		System.out.println("\n");
		
		main.test(main.bubbleSort, 3);
		System.out.println("\n");
		
		main.test(main.shellSort, 3);
		System.out.println("\n");
		
		//System.out.println(main.insertSort.toString()); // wypisanie posortowanej tablicy przez algorytm select Sort

	}

}
