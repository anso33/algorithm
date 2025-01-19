package a_data_structure;

public interface Stack {
	int push(int v);

	int pop();

	int peek();

	int indexOf(int v);

	void clear();

	boolean isEmpty();

	boolean isFull();
}
