package boj16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {

	static long A, B;
	static int c = Integer.MAX_VALUE;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
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

			if (current.v > B) continue;
			if (current.v == B) {
				if (c > current.c + 1) c = current.c + 1;
				return;
			}

			stack.push(new Node(current.v * 10 + 1, current.c + 1));
			stack.push(new Node(current.v * 2, current.c + 1));
		}

		if (c == Integer.MAX_VALUE) c = -1;
	}

	public static void main(String[] args) throws IOException {
		input();
		dfs();
		System.out.println(c);
	}
}
