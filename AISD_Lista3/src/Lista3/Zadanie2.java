package Lista3;

import java.util.ArrayList;
import java.util.List;

public class Zadanie2 implements Stack {
	
	 List<Double> array = new ArrayList<Double>();
	 int topIndex = -1;
	 
	 @Override
	 public boolean isEmpty() {
		 return array.size()==0;
	 }
	 
	 @Override
	 public boolean isFull() {
		 return false;
	 }
	 
	 @Override
	 public double pop() {
	  if(!isEmpty()) {
		  double liczba = array.get(topIndex);
		  array.remove(topIndex--);
		  return liczba;
	  }
	  return 0;
	 }
	 
	 @Override
	 public void push(double elem){
		  array.add(elem);
		  topIndex++;
	 }
	 
	 @Override
	 public int size() {
		 return array.size();
	  }
	 
	 @Override
	 public double top() {
	  if(!isEmpty()) {
		  return array.get(topIndex);
	  }
	  return 0;
	 }
	 
	 public boolean isParsable(String znak) {
		 try {
			 Double.parseDouble(znak);
		 }catch(NumberFormatException e) {
			 return false;
		 }
		 return true;
	 }
	 
	 void dodaj() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = l1 + l2;
		 push(wynik);
	 }
	 
	 void odejmij() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = l1 - l2;
		 push(wynik);
	 }
	 
	 void pomnoz() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = l1 * l2;
		 push(wynik);
	 }
	 
	 void podziel() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = 0;
		 if(l2 != 0 ) {
			 wynik = l1 / l2;
		 }
		 else throw new IllegalArgumentException("Nie mo¿na dzielic przez zero!");
		 push(wynik);
	 }
	 
	 void poteguj() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = 0;
		 if(l1 < 0 && l2 > 0 && l2 < 1) {
			 throw new IllegalArgumentException("Liczba mieœci siê w przestrzeni licz urojonych!");
		 }
		 else wynik = Math.pow(l1, l2);
		 push(wynik);
	 }
	 
	 void logarytm() {
		 double l2 = pop();
		 double l1 = pop();
		 double wynik = 0;
		 if(l2 > 0 && l2 != 1 && l2 > 0) {
			 wynik = Math.log(l1)/Math.log(l2); // liczba logarytmowana / podstawa logarytmu
		 }
		 else {
			 throw new IllegalArgumentException("Z³a sk³adnia logarytmu!");
		 }
		 push(wynik);
	 }
	 
	 void count(String funkcja){
		 
			 while(funkcja.length() > 0) {
				 char current = '0';
				 String znak = "";
				 while(current != ',' && funkcja.length() > 0) {
					 current = funkcja.charAt(0);
					 if(current != ',') {
						 znak = znak + current;
					 }
					 funkcja = funkcja.substring(1, funkcja.length());
				 }
				 
					 if(isParsable(znak)) {
						 push(Double.parseDouble(znak));
					 }
					 else {
						 if(size() > 1) {
							 switch(znak) {
							 case "+" :
								 dodaj();
								 break;
							 case "-" : 
								 odejmij();
								 break;
							 case "*" : 
								 pomnoz();
								 break;
							 case "/" :
								 podziel();
								 break;
							 case "^" : 
								 poteguj();
								 break;
							 case "l" : 
								 logarytm();
								 break;
							 default : throw new IllegalArgumentException("Zla operacja!");
							 }
						 }
						 else {
							 throw new IllegalArgumentException("Zla skladnia!");
						 }
					 }
			 	}
			 
			 if(size() != 1) {
				 throw new IllegalArgumentException("Zla skladnia!");
			 }
			 else {
				 System.out.println(top());
			 }
			 
	 }
}


