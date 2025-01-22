package a_data_structure;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap { // 최소 힙
	public List<Integer> elements;

	public BinaryHeap() {
		elements = new ArrayList<>();
		elements.add(null); // index = 0 -> null
	}

	public void swap(int i, int j) {
		Integer tmp = elements.get(i);
		elements.set(j, elements.get(i));
		elements.set(i, tmp);
	}

	// to insert
	public void upHeap() {
		int i = elements.size() - 1;
		int parentIndex = i / 2;

		while (parentIndex > 0) {
			if (elements.get(parentIndex) > elements.get(i)) {
				swap(i, parentIndex);
			}
			i = parentIndex;
			parentIndex = i / 2;
		}
	}

	public void insert(int k) {
		elements.add(k);
		upHeap();
	}

	// to pop
	public void downHeap(int i) {
		int left = i * 2;
		int right = i * 2 + 1;
		int smallest = i; // 현재 노드를 가장 작은 값으로 가정

		if (left <= elements.size() - 1 && elements.get(left) < elements.get(smallest)) smallest = left;
		if (right <= elements.size() - 1 && elements.get(right) < elements.get(smallest)) smallest = right;
		if (smallest != i) { // 나보다 더 작은 원소가 있다면 스왑
			swap(smallest, i);
			downHeap(smallest);
		}
	}

	public Integer extract() {
		int res = elements.get(1);

		elements.set(1, elements.get(elements.size() - 1));
		elements.remove(elements.size() - 1);
		downHeap(1);

		return res;
	}
}
