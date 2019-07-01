package Lista2;

public class Main {
	
	LinkedList lista = new LinkedList();
	OrganisedLinkedList lista2 = new OrganisedLinkedList();
	OrganisedArrayList lista3 = new OrganisedArrayList();
	Samochod auto1 = new Samochod("Ford", "Focus", 2005, 150000);
	Samochod auto2 = new Samochod("Opel", "Astra", 2011, 99000);
	Samochod auto3 = new Samochod("Mercedes Benz", "Klasa S", 2018, 1000);
	Samochod auto4 = new Samochod("BMW", "X5", 2015, 30000);
	Samochod auto5 = new Samochod("Fiat", "Panda", 2010, 110000);
	Samochod auto6 = new Samochod("Nissan", "Quashqai", 2014, 25000);
	
	public static void main(String[] args) {
	
		Main main = new Main();
		
		
		System.out.println("Zadanie 1: ");
		main.lista.add(main.auto1); //Ford
		main.lista.add(main.auto2); //Opel
		main.lista.add(main.auto3); //Mercedes
		main.lista.add(main.auto4); //BMW
		main.lista.add(main.auto5); //Fiat
		
		main.lista.setAktualny(main.lista.get(2));
		
		main.lista.add(main.auto6); // Nissan
		main.lista.add(main.auto1); // Ford
		main.lista.add(main.auto1); // Ford
		main.lista.add(main.auto5); // Fiat
		
		main.lista.setAktualny(main.lista.get(2)); // ustawienie "wskaznika" na element 2 w tablicy (czyli pojazd -> mercedes)
		
		main.lista.delete(main.lista.getAktualny()); // usuniecie mercedesa i ustawienie wskaznika na kolejny element czyli Nissana
		main.lista.add(main.auto1); // Ford
		
		main.lista.wypisz();
			
		System.out.println("\n");
		
		System.out.println("Zadanie 2: ");
		main.lista2.add(main.auto1); // 2005
		main.lista2.add(main.auto2); // 2011
		main.lista2.add(main.auto3); // 2018
		main.lista2.delete(main.lista2.get(3)); // usuwamy element 3 w tablicy, który jest autem z 2011 roku (0 - head, 1- tail)
		main.lista2.add(main.auto2); // 2011
		main.lista2.add(main.auto1); // 2005
		main.lista2.add(main.auto3); // 2018
		main.lista2.add(main.auto2); // 2011
		main.lista2.add(main.auto5); // 2010
		main.lista2.add(main.auto6); // 2014
		
		main.lista2.wypisz();
		
		System.out.println("\n");
		
		System.out.println("Zadanie 3:");
		
		main.lista3.add(main.auto1); // 2005
		main.lista3.add(main.auto2); // 2011
		main.lista3.add(main.auto3); // 2018
		main.lista3.delete(1); // usuwamy element 1 w tablicy, który jest autem z 2011 roku
		main.lista3.add(main.auto2); // 2011
		main.lista3.add(main.auto1); // 2005
		main.lista3.add(main.auto3); // 2018
		main.lista3.add(main.auto2); // 2011
		main.lista3.add(main.auto5); // 2010
		main.lista3.add(main.auto6); // 2014
		
		main.lista3.wypisz();
		
		
		System.out.println("\n");
		
		System.out.println("Wydajnosc listy wi¹zanej to: " + main.lista2.performance + ", a wydajnosc listy tablicowej to: " + main.lista3.performance);
		System.out.println("Im mniej tym lepiej");
		System.out.println("Lista wi¹zana jest wydajniejsza ze wzglêdu na brak koniecznoœci przesuwania elementów w tablicy");
		
	}

}
