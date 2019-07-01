package Lista3;

import java.util.Scanner;

public class Main {
	
	Zadanie1 task1 = new Zadanie1(4);
	Zadanie2 task2 = new Zadanie2();
	Zadanie3 task3 = new Zadanie3(5);
	Scanner scan = new Scanner(System.in);
	

	public static void main(String[] args) {
		
		Main main = new Main();
		
		System.out.println("Zadanie 1:");
		
		System.out.println("\n");
		
		System.out.println("Zosta³a stworzona kolejka ograniczona o wielkosci 4. Zape³niam elementami...");
		main.task1.enqueue(new Proces(1));
		main.task1.enqueue(new Proces(2));
		main.task1.enqueue(new Proces(3));
		main.task1.enqueue(new Proces(4));
		main.task1.enqueue(new Proces(5));
		
		main.task1.write();
		
		System.out.println("\n");
		
		System.out.println("Pierwsze dwa elementy zostan¹ usuniêtê...");
		
		main.task1.dequeue();
		main.task1.dequeue();
		
		main.task1.write();
	
		System.out.println("\n");
		
		System.out.println("Zadanie 2: ");
		
		System.out.println("\n");
		
		
        System.out.println("Wpisz operacje matematyczna w ONP: (+, -, *, /, ^, l)");
        String ONP = main.scan.nextLine();
        main.task2.count(ONP);
		
System.out.println("Zadanie 3:");
		
		System.out.println("\n");
		
		System.out.println("Zosta³ stworzony stos ton¹cy o wielkosci 5. Dodaje kolejne elementy...");
		main.task3.push(new Proces(1));
		main.task3.push(new Proces(2));
		main.task3.push(new Proces(3));
		main.task3.push(new Proces(4));
		main.task3.push(new Proces(5));
		
		main.task3.wypisz();
		
		System.out.println("\nDodaje kolejne elementy...\n");
		
		main.task3.pop();
		 
		main.task3.push(new Proces(6));
		main.task3.push(new Proces(7));
		main.task3.push(new Proces(8));
		main.task3.push(new Proces(9));
		
		main.task3.wypisz();		
		
	}

}
