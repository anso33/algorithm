package b_sorting.leetcode148;

class Solution {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;

		// 연결 리스트의 중앙과 끝노드 알아내기
		ListNode half = null, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			half = slow;
			slow = slow.next; // 중앙노드
			fast = fast.next.next;
		}
		half.next = null; // 연결리스트를 두개로 분할

		ListNode l1 = sortList(head); // 앞쪽 연결리스트
		ListNode l2 = sortList(slow); // 뒷쪽 연결리스트

		return mergeTwoList(l1, l2);
	}

	// l1에 l2를 정렬하면서 붙인다.
	public ListNode mergeTwoList(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		if (l2 == null) return l1;

		// 앞의 노드 값이 더 크면 swap
		if (l1.val > l2.val) {
			ListNode temp = l1;
			l1 = l2;
			l2 = temp;
		}
		l1.next = mergeTwoList(l1.next, l2);
		return l1;
	}

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
