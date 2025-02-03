package pg86971;

import java.util.*;

class Solution {

	public int getParent(int[] arr, int i) {
		if (arr[i] == i) return i;
		arr[i] = getParent(arr, arr[i]);
		return arr[i];
	}


	public int findDiff(int[] top) {
		int p1, p2, c1, c2;
		p1 = p2 = c1 = c2 =0;
		p1 = getParent(top, 1);
		c1++;
		for (int i = 2; i < top.length; i++) {
			int root = getParent(top, i);
			if (root == p1) {
				c1++;
			} else {
				p2 = root;
				c2++;
			}
		}

		System.out.println("c1 " + c1 + " c2 " + c2);
		if (c1 >= c2) return c1 - c2;
		else return c2-c1;
	}

	public int solution(int n, int[][] wires) {
		int[] top = new int[n + 1];
		for (int i = 0; i < n; i++) {
			top[i] = i;
		}
		for (int i = 0; i < wires.length; i++) {
			top[wires[i][1]] = wires[i][0]; // 부모 노드를 어떻게 정하지?
		}

		int dif = n;
		for (int i = 1; i < top.length; i++) {
			int tmp = i;
			if (top[i] != i) {
				tmp = top[i];
				top[i] = i;
			}
			dif = Math.min(dif, findDiff(top));
			top[i] = tmp;
			System.out.println("tmp " + tmp);
		}

		return dif;
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
		System.out.println(new Solution().solution(n, wires));
	}
}
