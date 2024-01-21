
/** this class represents a power set. Each power set has an array of Set objects */

public class PowerSet <T> {

	/** Set array for set objects */
	private Set<T>[] set;
	
	/** constructor that creates the sets and adds them to the array */
	public PowerSet(T[] elements) {
		
		
		int powerSetLength = 1; 
		for (int i = 0; i < elements.length; i++) { // loops through the length of the given array and multiples 2 to the exponent of the length of the array
			powerSetLength = powerSetLength * 2;
		}
		
		set = new Set[powerSetLength]; // creates the set array with two to the exponent of the length of the array

		for (int i = 0; i < powerSetLength; i++) { // loops through the length of the newly created set
			
			String binary = Integer.toBinaryString(i); // takes each number from 0 to n-1 and turns it into a binary string
			
			if (binary.length() != elements.length) { // the length of the binary string must equal the length of the array
				for(int j = binary.length(); j < elements.length; j++) { // if it doesn't a for loop is run that adds zero to the front of the binary string
					binary = "0" + binary;
				}
			}
			
			Set<T> binarySet = new Set();
			
			for (int k = 0; k < binary.length(); k++) { // loops through the length of the binary string
				String binaryValue = Character.toString(binary.charAt(k)); // takes each character in the string and converts them to a string
				if (binaryValue.equals("1")) { // with the character converted to a string, .equals can be used to see if that character is equal to one
					binarySet.add(elements[k]); // if it equals one, the same index is used to take the value from the original list at the index where a one is found
				}
			}
			set[i] = binarySet; 
		}
	}
	/** public method that returns the length of the power set */
	public int getLength() {
		return set.length;
	}
	/** public method that returns a specific set in the power set */
	public Set<T> getSet(int i) {
		return set[i];
		
	}
	
}
