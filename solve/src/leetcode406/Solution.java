package leetcode406;

import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
	public int[][] reconstructQueue(int[][] people) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(
			(a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]
		);

		int length = people.length;
		for (int[] p : people) {
			pq.add(p);
		}

		LinkedList<int[]> list = new LinkedList<>();
		while (!pq.isEmpty()) {
			int[] p = pq.poll();
			list.add(p[1], p);
		}

		// int[][] result = new int[length][];
		// for (int i = 0; i < length; i++) {
		//     result[i] = list.get(i);
		// }
		// return result;
		return list.toArray(new int[length][2]);
	}
}

