package boj16953;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

	static int A, B;
	static int c = Integer.MAX_VALUE;

	static void input() {
		Scanner scan = new Scanner(System.in);
		A = scan.nextInt();
		B = scan.nextInt();
	}

	public static class Node {
		long v;
		int c;

		public Node(long v, int c) {
			this.v = v;
			this.c = c;
		}
	}

	static void dfs() {

		Deque<Node> stack = new ArrayDeque<>();
		stack.push(new Node(A, 0));

		while (!stack.isEmpty()) {
			Node current = stack.pop();

			if (current.v == B) {
				if (c > current.c + 1) c = current.c + 1;
				return;
			}

			if (current.v * 10 + 1 <= B) {
				stack.push(new Node(current.v * 10 + 1, current.c + 1));
			}
			if (current.v * 2 <= B) {
				stack.push(new Node(current.v * 2, current.c + 1));
			}
		}

		if (c == Integer.MAX_VALUE) c = -1;
	}

	public static void main(String[] args) {
		input();
		dfs();
		System.out.println(c);
	}
}
