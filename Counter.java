
/** this class represents a counter. each counter has a starter card and a power set */

public class Counter {
	/** power set for the cards */
	private PowerSet<Card> cardps;
	/** starter card used in every hand */
	private Card starter;

	/** constructor that initializes the starter card and creates the power set using the Card array */
	public Counter(Card[] hand, Card starter) {
		this.starter = starter;
		cardps = new PowerSet<Card>(hand);
	}

	/** public method that calculates the number of points in the given hand by calling the private helper methods */
	public int countPoints() {

		int points = 0;
		int maxFlush = 0;
		int largestRun = 3; // set as three as a run cannot be less than three
		int numOfRuns = 0;

		for (int i = 0; i < cardps.getLength(); i++) { // loops through the power set
			
			if (isRun(cardps.getSet(i))) { // checks if the current set is a run of three or more
				
				if (cardps.getSet(i).getLength() == largestRun) { // if the current run is the largest run the number of runs is increased
					numOfRuns++;
				}

				else if (cardps.getSet(i).getLength() > largestRun) { // if it isn't, a new largest run is created and the number of runs is one
					largestRun = cardps.getSet(i).getLength();
					numOfRuns = 1;
				}
			}
			
			if (is4Flush(cardps.getSet(i)) && maxFlush != 5) { // checks if there is a flush of four. dosen't run if the max flush is five 
				maxFlush = 4;
				
			}
			
			if (is5Flush(cardps.getSet(i))) { // if there is a flush of five the max flush is set to 5
				maxFlush = 5;
			}
				
			if (isFifteen(cardps.getSet(i))) { // if the given set of cards equals 15, 2 points are added
				points += 2;
			}

			if (isHisKnobs(cardps.getSet(i))) { // checks for his knobs
				points += 1;
			}

			if (isPair(cardps.getSet(i))) { // checks for a pair
				points += 2;
			}
		}
		
		for (int i = 0; i < numOfRuns; i++) { // adds the number of the largest run to points for the number of runs
			points += largestRun;
		}
		
		points += maxFlush; // adds the max flush to points

		return points;

	}

	
	private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.

		int n = set.getLength();

		if (n <= 2) return false; // Run must be at least 3 in length.

		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}

	/** private method that checks if the given set has a flush of 4 */
	private boolean is4Flush(Set<Card> set) {

		if (set.getLength() == 4 ) { // checks if the length of the set is 4 and if all the cards in the set have the same suit
			if (set.getElement(0).getSuit() == set.getElement(1).getSuit() && set.getElement(0).getSuit() == set.getElement(2).getSuit() && set.getElement(0).getSuit() == set.getElement(3).getSuit()) {
					return true;
				}
			}
		
		return false;
	}
	
	/** private method that checks if the given set has a flush of 5 */
	private boolean is5Flush(Set<Card> set) {
		
		if (set.getLength() == 5) { // checks if the length of the set is 5 and if all the cards in the set have the same suit
			if (set.getElement(0).getSuit() == set.getElement(1).getSuit() && set.getElement(0).getSuit() == set.getElement(2).getSuit() && set.getElement(0).getSuit() == set.getElement(3).getSuit() && set.getElement(0).getSuit() == set.getElement(4).getSuit()) {
				return true;
			}
		}
		
		return false;
	}
	
    /** private method that checks if the given set is equal to 15 */
	private boolean isFifteen(Set<Card> set) {

		int total = 0;

		for (int i = 0; i < set.getLength(); i++) { // loops through the given set 
			total = total + set.getElement(i).getFifteenRank(); // adds the fifteen rank to the total
		}

		if (total == 15) {
			return true;
		}

		return false;

	}

	/** private method that checks for a jack with the same suit as the starter card */
	private boolean isHisKnobs(Set<Card> set) { 

		if (set.getLength() == 1) { // only runs if the set length is one so each card is only checked once
			if (set.getElement(0).getRunRank() == 11 && set.getElement(0).getSuit().equals(starter.getSuit())) {
				
				return true;
			}
		}
				
		return false;
	}

	/** private method that checks if the given set is a pair  */
	private boolean isPair(Set<Card> set) {

		if (set.getLength() == 2) { // runs if the set length is two so only the sets with two cards can be checked 
			if (set.getElement(0).getLabel().equals(set.getElement(1).getLabel())) {
				return true;
			}
		}
		return false;
	}
}
