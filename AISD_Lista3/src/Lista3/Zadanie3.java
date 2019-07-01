package Lista3;

	public class Zadanie3 implements Stack2 {
		 private static final int DEFAULT_CAPACITY = 16;
		 Proces array[];
		 int topIndex;

		 public Zadanie3 (int initialSize){
			 array=new Proces[initialSize];
			 topIndex=0;
		 }
		 
		 public Zadanie3 (){
			 this(DEFAULT_CAPACITY);
		 }
		 
		 @Override
		 public boolean isEmpty() {
			 return topIndex==0;
		  }
		 
		 @Override
		 public boolean isFull() {
			 return topIndex==array.length;
		  }
		 
		 @Override
		 public Proces pop() {
		  if(!isEmpty()) {
			  return array[--topIndex];
		  }
		  return null;
		  }
		 
		 @Override
		 public void push(Proces elem){
		  if(!isFull()) {
			  array[topIndex++]=elem;
		  }
		  else {
			  for(int i = 0; i < topIndex-1; i++) {
				  array[i] = array[i+1];
			  }		  
			  array[topIndex-1] = elem;
		  }
		 }
		 
		 @Override
		 public int size() {
			 return topIndex;
		  }
		 
		 @Override
		 public Proces top(){
		  if(!isEmpty()) {
			  return array[topIndex-1];
		  	}
		  return null;
		  }
		 
		 public void wypisz() {
			 for(int i = 0; i < topIndex; i++) {
				 System.out.println("PID: " + array[i].getPID() + ", czas wykonania: " + array[i].getCzasWykonania());
			 }
		 }
	}