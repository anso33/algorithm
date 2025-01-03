package boj_1260;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	static StringBuilder sb = new StringBuilder();

	static int N, M, V;
	static ArrayList<Integer>[] adj;
	static boolean[] visit;

	static void input() {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		V = scan.nextInt();
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			adj[x].add(y);
			adj[y].add(x);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
	}

	static void dfs(int x) {
		visit[x] = true;
		sb.append(x).append(' ');

		for (int y : adj[x]) {
			if (visit[y]) {
				continue;
			}
			System.out.println(y);
			dfs(y);
		}
	}

	// static void dfs(int start) {
	static void interativeDfs(int start) {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(start);
		visit[start] = true;

		while (!stack.isEmpty()) {
			int x = stack.pop();
			sb.append(x).append(' ');

			for (int i = adj[x].size() - 1; i >= 0; i--) {
				int y = adj[x].get(i);
				if (!visit[y]) {
					System.out.println(y);
					stack.push(y);
					visit[y] = true;
				}
			}
		}
	}

	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();

		que.add(start);
		visit[start] = true;

		while (!que.isEmpty()) {
			int x = que.poll();
			sb.append(x).append(' ');
			for (int y : adj[x]) {
				if (visit[y]) {
					continue;
				}
				que.add(y);
				visit[y] = true;
			}
		}
	}

	public static void main(String[] args) {
		input();

		visit = new boolean[N + 1];
		dfs(V);
		sb.append('\n');
		for (int i = 1; i <= N; i++) {
			visit[i] = false;
		}
		bfs(V);
		System.out.println(sb);
	}
}

