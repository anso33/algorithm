package pg150367;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	public int[] solution(long[] numbers) {
		int[] answer = new int[numbers.length];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = sol(numbers[i]);
		}

		return answer;
	}

	public int sol(long number) {
		String binary = Long.toBinaryString(number);

		int length = binary.length();
		int nodeCount = 1;
		int level = 1;
		while (length > nodeCount) {
			level *= 2;
			nodeCount += level;
		}
		int offset = nodeCount - length;
		String fullBinary = "0".repeat(offset) + binary;

		// System.out.println(fullBinary);
		Deque<String> stack = new ArrayDeque<>();
		stack.push(fullBinary);

		while (!stack.isEmpty()) {
			String current = stack.pop();
			int len = current.length();

			if (len == 0) continue;

			int root = len / 2;
			String leftSubTree = current.substring(0, root);
			String rightSubTree = current.substring(root + 1);

			if (current.charAt(root) == '0') {
				if (!isZeroTree(leftSubTree) || !isZeroTree(rightSubTree)) {
					return 0;
				}
			} else {
				stack.push(leftSubTree);
				stack.push(rightSubTree);
			}
		}

		return 1;
	}

	private boolean isZeroTree(String binary) {
		for (char c : binary.toCharArray()) {
			if (c == '1') {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		// [7, 42, 5]
		// long[] numbers = {7, 42, 5};
		// [63, 111, 95]
		long[] numbers = {63, 111, 95};
		int[] answer = sol.solution(numbers);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}
