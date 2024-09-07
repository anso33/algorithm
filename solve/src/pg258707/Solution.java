package pg258707;

import java.util.*;

class MyCard {
	Set<Integer> cards;
	int cardSetCount;
	int coin;

	public MyCard(int[] cards, int coin) {
		this.cards = new HashSet<>();
		for (int card : cards) {
			addCard(cards.length * 3 + 1, card);
		}
		this.coin = coin;
	}

	public void addCard(int sum, int cardValue) {
		if (!cards.contains(sum - cardValue)) {
			cards.add(cardValue);
		} else {
			cards.remove(sum - cardValue);
			cardSetCount++;
		}
		System.out.println("card is " + cardValue + " now " + cardSetCount);
	}

	public boolean canSubmitCards() {
		return cardSetCount > 0;
	}

	public void submitCards() {
		cardSetCount--;
	}

	public boolean decideToPurchaseCard(int n, int cardValue) {
		if (coin > 0) {
			return cards.contains(n - cardValue);
		}
		return false;
	}

	public void purchaseCard(int n, int cardValue) {
		if (coin > 0) {
			addCard(n, cardValue);
			coin--;
		}
	}
}

class Solution {
	public int solution(int coin, int[] cards) {
		int cardCount = cards.length;
		MyCard mycard = new MyCard(Arrays.copyOfRange(cards, 0, cardCount / 3), coin);

		List<Integer> temp = new ArrayList<>();
		int round = 1;
		for (int i = cardCount / 3; i < cardCount; i += 2) {
			boolean isSubmit = false;

			if (mycard.decideToPurchaseCard(cardCount + 1, cards[i])) {
				mycard.purchaseCard(cardCount + 1, cards[i]);
				mycard.submitCards();
				isSubmit = true;
			} else {
				temp.add(cards[i]);
			}

			if (mycard.decideToPurchaseCard(cardCount + 1, cards[i + 1])) {
				mycard.purchaseCard(cardCount + 1, cards[i + 1]);
				if (!isSubmit) {
					mycard.submitCards();
					isSubmit = true;
				}
			} else {
				temp.add(cards[i + 1]);
			}

			if (!isSubmit) {
				if (mycard.canSubmitCards()) {
					mycard.submitCards();
					isSubmit = true;
				}
			}

			if (!isSubmit && mycard.coin > 1) {
				for (int tempCard : temp) {
					if (temp.contains(cardCount + 1 - tempCard)) {
						isSubmit = true;
						mycard.coin -= 2;
						temp.remove((Integer) tempCard);
						temp.remove((Integer) (cardCount + 1 - tempCard));
						break;
					}
				}
			}

			if (!isSubmit) break;
			round++;
		}
		return round;
	}
}