package a_data_structure;

public class SetArr implements Set {
	int[] elements;
	int capacity;
	int size;

	public SetArr(int capacity) {
		elements = new int[capacity];
		this.capacity = capacity;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public boolean contains(int v) {
		for (int i=0; i < size; i++) {
			if (elements[i] == v) return true;
		}
		return false;
	}

	@Override
	public boolean add(int v) {
		if (size >= capacity) return false;
		if (contains(v)) return false;

		elements[size] = v;
		size++;
		return true;
	}

	@Override
	public boolean remove(int v) {
		if (size <= 0) return false;
		if (!contains(v)) return false;

		for (int i = 0; i < size; i++) {
			if (elements[i] == v) {
				elements[i] = elements[size - 1];
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		size = 0;
	}
}
