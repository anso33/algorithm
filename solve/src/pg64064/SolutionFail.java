package pg64064;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class SolutionFail {
	int answer = 0;
	ArrayList<Integer>[] n;
	boolean[] visit;

	public int solution(String[] user_id, String[] banned_id) {
		n = new ArrayList[banned_id.length];
		visit = new boolean[user_id.length];
		for (int i = 0; i < banned_id.length; i++) {
			n[i] = new ArrayList<>();
		}

		for (int i = 0; i < banned_id.length; i++) {
			for (int j = 0; j < user_id.length; j++) {
				if (isBannedNickname(banned_id[i], user_id[j])) n[i].add(j);
			}
		}

		dfs();

		return answer;
	}

	public void dfs() {
		Deque<ArrayList<Integer>> stack = new ArrayDeque<>();
		for (Integer i: n[0]) {
			ArrayList<Integer> l = new ArrayList<>(n.length);
			l.add(i);
			visit[i] = true;
			stack.push(l);
		}

		while (!stack.isEmpty()) {
			ArrayList<Integer> cur = stack.pop();
			if (cur.size() == n.length) {
				answer++;
				continue;
			}

			for (Integer i : n[cur.size()]) {
				if (cur.contains(i)) continue;
				if (visit[i]) continue;
				ArrayList<Integer> l = new ArrayList<>(cur);
				l.add(i);
				stack.push(l);
			}
		}
	}

	public boolean isBannedNickname(String ban, String t) {
		if (ban.length() != t.length()) return false;
		for (int i = 0; i < ban.length(); i++) {
			if (ban.charAt(i) == '*') continue;
			if (ban.charAt(i) != t.charAt(i)) return false;
		}
		return true;
	}

	public static void main(String[] args) {
// ["frodo", "fradi", "crodo", "abc123", "frodoc"] ["*rodo", "*rodo", "******"]
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};

		SolutionFail sol = new SolutionFail();
		System.out.println(sol.solution(user_id, banned_id)); // 2
	}
}
