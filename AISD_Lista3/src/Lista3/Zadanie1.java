package Lista3;


public class Zadanie1 implements Queue{

	private static final int DEFAULT_CAPACITY = 16;
	private Proces array[];
	private int beginIndex = 0;
	private int endIndex = 0;
	
	public Zadanie1(int size) {
		if(size < 1) {
			throw new IllegalArgumentException("Wielkoœc kolejki musi byc wieksza niz 0!");
		}
		
		else {
			array=new Proces[size+1];
		}
	}
	
	public Zadanie1() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean isEmpty() {
		return beginIndex==endIndex;
	}
	
	@Override
	public boolean isFull() {
		return beginIndex==(endIndex+1)%array.length;
	}
	
	@Override
	 public Proces dequeue(){
		if(!isEmpty()) {
			Proces retValue=array[beginIndex++]; 
			beginIndex%=array.length;
			return retValue;
		}
	 return null;
	 }
	
	 @Override
	 public void enqueue(Proces elem){
		 if(!isFull()) {
			 array[endIndex++]=elem;
		 	 endIndex%=array.length;
		 }
	 }
	 
	 @Override
	 public int size() {
		 return (endIndex+array.length-beginIndex) % array.length;
	 }
	 
	 @Override
	 public Proces first() {
		 if(!isEmpty()) {
			 return array[beginIndex];
		 }
	 return null;
	 }
	 
	 public void write() {
		 int i = beginIndex;
		 while(i!=endIndex) {
			 System.out.println("PID: " + array[i].getPID() + ", czas wykonania: " + array[i++].getCzasWykonania());
			 i %= array.length;
		 }
	 }
	}