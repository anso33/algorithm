package leetcode17;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public static List<String> result = new ArrayList<>();
	public static Map<Character, List<Character>> dic = new HashMap<>();

	public void dfs(String digits) {
		Deque<String> stack = new ArrayDeque<>();
		stack.push("");

		while (!stack.isEmpty()) {
			String s = stack.pop();
			if (s.length() == digits.length()) {
				result.add(s);
				continue;
			}

			for (char c : dic.get(digits.charAt(s.length()))) {
				stack.push(s + c);
			}
		}

	}

	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) return result;

		dic.put('0', List.of());
		dic.put('1', List.of());
		dic.put('2', List.of('a', 'b', 'c'));
		dic.put('3', List.of('d', 'e', 'f'));
		dic.put('4', List.of('g', 'h', 'i'));
		dic.put('5', List.of('j', 'k', 'l'));
		dic.put('6', List.of('m', 'n', 'o'));
		dic.put('7', List.of('p', 'q', 'r', 's'));
		dic.put('8', List.of('t', 'u', 'v'));
		dic.put('9', List.of('w', 'x', 'y', 'z'));

		dfs(digits);
		return result;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.letterCombinations("23"));
	}
}
