package a_data_structure;

public class QueueArr implements Queue{
	private int[] elements;
	private int size;
	private int capacity;
	private int front;
	private int rear;

	public QueueArr(int capacity) {
		elements = new int[capacity];
		this.capacity = capacity;
		size = front = rear = 0;
	}

	@Override
	public int enque(int v) {
		if (size >= capacity)
			throw new IllegalArgumentException();
		elements[rear] = v;
		rear = (rear + 1) % capacity;
		size++;
		return v;
	}

	@Override
	public int deque() {
		if (size <= 0)
			throw new IllegalArgumentException();
		int res = elements[front];
		size--;
		front = (front + 1) % capacity;
		return res;
	}

	@Override
	public int peek() {
		if (size == 0)
			throw new IllegalArgumentException();
		return elements[front];
	}

	@Override
	public int indexOf(int v) {
		int index = front;
		for (int j = 0; j < size; j++) {
			if (elements[index] == v) return j;
			index = (index + 1) % capacity;
		}
		return -1;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}

	@Override
	public boolean isFull() {
		return size >= capacity;
	}
}
