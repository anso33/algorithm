package a_data_structure;

import java.util.ArrayDeque;

public class StackArr implements Stack {
	private int max;
	private int size;
	private int[] elements;

	public StackArr(int capacity) {
		elements = new int[capacity];
		max = capacity;
		size = 0;
	}

	public int push(int v) {
		elements[size] = v;
		size++;
		return v;
		// return elements[size++] = v;
	}

	public int pop() {
		int res = elements[size - 1];
		size--;
		return res;
		// return elements[--size];
	}

	public int peek() {
		return elements[size - 1];
	}

	public int indexOf(int v) {
		for (int i = 0; i < size; i++) {
			if (elements[i] == v) return i;
		}
		return -1;
	}

	public void clear() {
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
		// return size <= 0;
	}

	public boolean isFull() {
		return (size == max);
		// return size >= max;
	}
}
