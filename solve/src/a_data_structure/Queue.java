package a_data_structure;

public interface Queue {
	int enque(int v); // offer

	int deque(); // poll

	int peek();

	int indexOf(int i);

	void clear();

	boolean isEmpty();

	boolean isFull();
}
