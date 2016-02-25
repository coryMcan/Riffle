import java.util.HashSet;
import java.util.Random;

/**
 * This program creates a standard card deck, shuffles it using random access on an int array, then shuffles again using the
 * "Riffle" method, where you divide the deck into two 'halves' which may be unequal in size and stack card by card from each half
 * until you have a shuffled deck.
 *
 * isSingleRiffled is a Riffle check that ensures all cards in the deck have been shuffled using the Riffle method.
 */

public class Riffle {
	/**
	 * Buffers to hold the result
	 */
	static StringBuffer h1 = new StringBuffer();
	static StringBuffer h2 = new StringBuffer();
	static StringBuffer shuffled = new StringBuffer();

	/**
	 * The 'topmost' card from either half must be the same as the 'topmost' in the shuffled deck to indicate a
	 * single Riffle. Otherwise it's not a single Riffle, return false.
	 */
	public static boolean isSingleRiffled(String[] shuffledDeck, String[] half1, String[] half2, int half1Index, int half2Index){

		/**
		 * Ensure topmost card in both halves is non-null
		 */
		if((half1[half1Index] != null && !half1[half1Index].isEmpty()) ||
				(half2[half2Index] != null && !half2[half1Index].isEmpty())){
			for(String s : shuffledDeck) {
				if(s != null && !s.isEmpty()){
					shuffled.append(s + " ");

					if (half1Index <= half1.length - 1
							&& half1[half1Index].equals(s)) {
						h1.append(half1[half1Index] + " ");
						half1Index += 1;
					} else if (half2Index <= half2.length - 1
							&& half2[half2Index].equals(s)) {
						h2.append(half2[half2Index] + " ");
						half2Index += 1;
					} else {
						return false;
					}
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
		printResults();
		return true;
	}

	public static void printResults(){
		System.out.println("h1: "+ h1);
		System.out.println("h2: "+ h2);
		System.out.println("shuffledDeck: "+ shuffled);
	}

	public static void main (String args[]){

		String[] freshDeck, shuffledDeck, half1, half2;
		freshDeck = new String[52];
		shuffledDeck = new String[52];
		half1 = new String[52];
		half2 = new String[52];
		String[] values = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		String[] suits = {"\u2660","\u2666","\u2663","\u2764"};

		System.out.println("Set up decks: ");

		/**
		 * Create freshDeck, all values matched with suits in array order
		 */
		int deckIdx = 0;
		for(int i = 0; i < values.length; i++){
			for(int j = 0; j < suits.length; j++){
				String card = values[i] + suits[j];
				if(card != null && !card.isEmpty()){
					freshDeck[deckIdx++] = card;
				}
			}
		}

		/**
		 * Shuffle by random array access, prevent duplicates with set
		 */
		Random r = new Random();
		HashSet e = new HashSet();
		for(int i = 0; i < shuffledDeck.length; i++){
			String card = freshDeck[r.nextInt(52)];
			if(!e.contains(card) && card != null && !card.isEmpty()){
				e.add(card);
				shuffledDeck[i] = card;
			}else{
				i--;
			}
		}

		/**
		 * Split the shuffledDeck into random and possibly unequal halves
		 */
		int half1idx = 0;
		int half2idx = 0;
		StringBuffer h1 = new StringBuffer();
		StringBuffer h2 = new StringBuffer();
		System.out.print("shuffledDeck: ");

		for(int i = 0; i < shuffledDeck.length; i++){
			if(r.nextBoolean()) {
				h1.append(shuffledDeck[i]+" ");
				half1[half1idx++] = shuffledDeck[i];
			}else {
				h2.append(shuffledDeck[i]+" ");
				half2[half2idx++] = shuffledDeck[i];
			}
			System.out.print(shuffledDeck[i] + " ");
		}
		int halvesSize = half1idx + half2idx;
		System.out.println("\nh1: "+ h1.toString());
		System.out.println("h2: "+ h2.toString());

		if(halvesSize < 52){
			throw new IllegalStateException();
		}

		System.out.println("\nresults: ");
		System.out.println("\n"+isSingleRiffled(shuffledDeck, half1, half2, 0, 0));
	}
}
