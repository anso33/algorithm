package pg178870;

class Solution {
	public int[] solution(int[] sequence, int k) {
		int[] answer = new int[]{0, sequence.length-1};

		int left = 0, right = 1, sum = sequence[0];
		while (left<right) {
			if (sum == k) {
				if (right - 1 - left < answer[1] - answer[0]) {
					answer[0] = left; answer[1] = right - 1;
				}
				sum -= sequence[left++];
			} else if (sum > k) {
				sum -= sequence[left++];
			} else if (right < sequence.length) {
				sum += sequence[right++];
			} else {
				break;
			}
		}

		return answer;
	}
}