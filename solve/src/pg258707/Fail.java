package pg258707;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MyCardFail {
	Set<Integer> cards;
	int cardSetCount;
	int coin;

	public MyCardFail(int[] cards, int coin) {
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

public class Fail {
	public int solution(int coin, int[] cards) {
		int cardCount = cards.length;
		MyCardFail mycard = new MyCardFail(Arrays.copyOfRange(cards, 0, cardCount / 3), coin);

		int round = 1;
		for (int i = cardCount / 3; i < cardCount; i += 2) {
			if (mycard.decideToPurchaseCard(cardCount + 1, cards[i])) {
				mycard.purchaseCard(cardCount + 1, cards[i]);
				round++;
				continue;
			} if (mycard.decideToPurchaseCard(cardCount + 1, cards[i + 1])) {
				mycard.purchaseCard(cardCount + 1, cards[i + 1]);
				round++;
				continue;
			}

			if (mycard.coin > 1 && (cards[i] + cards[i + 1]) == cardCount + 1) {
				mycard.coin -= 2;
				round++;
			} else {
				break;
			}

			mycard.submitCards();
			if (!mycard.canSubmitCards()) {
				break;
			}
		}
		return round;
	}
}
