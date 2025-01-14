package boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int A, B, time;
	static boolean[] visited = new boolean[100001];

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(A);

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int current = q.poll();
				visited[current] = true;

				if (current == B) return;

				if (current - 1 >= 0 && !visited[current - 1]) q.add(current - 1);
				if (current + 1 <= 100000 && !visited[current + 1]) q.add(current + 1);
				if (current * 2 <= 100000 && !visited[current * 2]) q.add(current * 2);
			}
			time++;
		}
	}

	public static void main	(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		bfs();

		System.out.println(time);
	}
}
