package pg258709;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	public static int[][] combination(int[] arr, int k) {
		List<int[]> result = new ArrayList<>();
		int n = arr.length;

		for (int i = 0; i < (1 << n); i++) {
			List<Integer> comb = new ArrayList<>();

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					comb.add(arr[j]);
				}
			}

			if (comb.size() == k) {
				result.add(comb.stream().mapToInt(Integer::intValue).toArray());
			}
		}

		return result.toArray(new int[result.size()][]);
	}

	// 테스트 코드
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		int k = 2;

		int[][] combinations = combination(arr, k);

		// 결과 출력
		for (int[] combination : combinations) {
			for (int num : combination) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
