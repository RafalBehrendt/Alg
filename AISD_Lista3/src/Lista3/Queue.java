package Lista3;

public interface Queue{
 boolean isEmpty();
 boolean isFull();
 Proces dequeue();
 void enqueue(Proces elem);
 int size();
 Proces first();
}

