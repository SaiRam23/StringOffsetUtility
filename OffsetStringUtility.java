package com.marketLogic.StringUtility;

//This is a String utility class that will alter the string characters as per the offset provided by the user.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @author Neha Gautam
 * @since 09-03-18
 *
 */

import org.apache.log4j.Logger;

/**
 * @author Neha Gautam
 * @since 10-03-18
 *
 */
public class OffsetStringUtility {

	// Declaring constants
	private static Logger logger = Logger.getLogger(OffsetStringUtility.class);
	public static final int MAX_CHARACTER_POSITIVE_RANGE = 26;
	public static final int MAX_CHARACTER_NEGATIVE_RANGE = -26;
	public static final int SMALL_UPPER_RANGE = 122;
	public static final int SMALL_LOWER_RANGE = 97;
	public static final int CAPITAL_UPPER_RANGE = 90;
	public static final int CAPITAL_LOWER_RANGE = 65;

	public static final char A = 'A';
	public static final char Z = 'Z';
	public static final char a = 'a';
	public static final char z = 'z';

	public static void main(String[] arg) {

		// Fetching input string and Offset values from the user.
		Scanner scannerObj = new Scanner(System.in);
		try {

			System.out.println("Enter the String to be altered based on the offset: ");
			String inputString = scannerObj.next();

			System.out.println("Enter the Offset for the String entered: ");
			String offset = scannerObj.next();

			logger.debug("User String to be altered based on the offset: " + inputString);
			logger.info("Calling Method encodeStringOffset() for string Manipulation.");
			if (!validateOffset(offset)) {
				offset = scannerObj.next();
			}
			if (validateOffset(offset) && validateString(inputString)) {
				char[] inputStringCharArr = inputString.toCharArray();
				encodeStringOffset(inputStringCharArr, offset);
				System.out.println("\n" + "Altered string: " + String.valueOf(inputStringCharArr));
			}

		} finally {
			scannerObj.close();
		}
	}

	/**
	 * This method will manipulate the string according to the offset and string
	 * provided.
	 * 
	 * @param string
	 * @param offset.
	 */
	public static String encodeStringOffset(char[] inputStr, String offset) {

		List<Integer> smallAsciiList = new ArrayList<Integer>(MAX_CHARACTER_POSITIVE_RANGE);
		List<Integer> capsAsciiList = new ArrayList<Integer>(MAX_CHARACTER_POSITIVE_RANGE);
		for (char capsCharacter = A; capsCharacter <= Z; capsCharacter++)
			capsAsciiList.add((int) capsCharacter);
		for (char character = a; character <= z; character++)
			smallAsciiList.add((int) character);
		logger.debug("Ascii list for 'a-z' and 'A-Z'" + smallAsciiList + "" + capsAsciiList);

		for (int counter = 0; counter < inputStr.length; counter++) {
			int asciiCode = (int) inputStr[counter];

			// checking for new offset ASCII codes ranges
			if (smallAsciiList.contains(asciiCode)) {

				int newOffsetAscii = asciiCode + Integer.valueOf(offset);
				if ((newOffsetAscii > SMALL_UPPER_RANGE || newOffsetAscii < SMALL_LOWER_RANGE)) {
					newOffsetAscii = convertValidOffset(newOffsetAscii);
				}
				while (!smallAsciiList.contains(newOffsetAscii)) {
					newOffsetAscii = asciiCode + calculateOffsetRange(offset);

				}
				inputStr[counter] = (char) (newOffsetAscii);
			} else if (capsAsciiList.contains(asciiCode)) {

				int newOffsetAscii = asciiCode + Integer.valueOf(offset);
				if ((newOffsetAscii > CAPITAL_UPPER_RANGE || newOffsetAscii < CAPITAL_LOWER_RANGE)) {
					newOffsetAscii = convertValidOffsetCaps(newOffsetAscii);
				}

				while (!capsAsciiList.contains(newOffsetAscii)) {
					newOffsetAscii = asciiCode + calculateOffsetRange(offset);
				}

				inputStr[counter] = (char) (newOffsetAscii);

			}
		}
		return inputStr.toString();
	}

	/**
	 * This method will validate the offset to be numeric to proceed with string
	 * manipulation.
	 * 
	 * @param offset
	 */
	private static boolean validateOffset(String offset) {

		try {
			if (offset != null)
				Integer.parseInt(offset);
		} catch (NumberFormatException ex) {
			logger.info(offset + " is not a valid offset.Please Enter valid offset.");
			System.out.println(offset + " is not a valid offset.Please enter valid numeric offset.");
			return false;
		}
		return true;

	}

	/**
	 * This method will calculate the offset to be in place with alphabets only.
	 * manipulation.
	 * 
	 * @param Integer.valueOf(obj)
	 *            offset
	 */
	private static int calculateOffsetRange(String offset) {

		int validOffset = Integer.valueOf(offset);
		// Checking for positive-negative offset and range greater than 26(count
		// of alphabetical series)
		if (validOffset > 0 && validOffset > MAX_CHARACTER_POSITIVE_RANGE) {
			validOffset = validOffset % MAX_CHARACTER_POSITIVE_RANGE;
		} else {
			validOffset = validOffset % MAX_CHARACTER_POSITIVE_RANGE;
			validOffset = validOffset + MAX_CHARACTER_POSITIVE_RANGE;
		}
		return validOffset;

	}

	/**
	 * This method will validate the offset to be numeric to proceed with string
	 * manipulation.
	 * 
	 * @param Integer.valueOf(obj)
	 *            offset
	 */
	private static int convertValidOffset(int newOffsetAscii) {

		int offset = newOffsetAscii;
		if (newOffsetAscii > SMALL_UPPER_RANGE) {
			int upperRangeOffset = newOffsetAscii % SMALL_UPPER_RANGE;
			while (newOffsetAscii > MAX_CHARACTER_POSITIVE_RANGE) {
				newOffsetAscii = upperRangeOffset % MAX_CHARACTER_POSITIVE_RANGE;

			}
			offset = newOffsetAscii % MAX_CHARACTER_POSITIVE_RANGE + SMALL_LOWER_RANGE - 1;
		} else if (newOffsetAscii < SMALL_LOWER_RANGE)
			offset = newOffsetAscii + MAX_CHARACTER_POSITIVE_RANGE;
		return offset;
	}

	/**
	 * This method will validate the offset to be numeric to proceed with string
	 * manipulation.
	 * 
	 * @param Integer.valueOf(obj)
	 *            offset
	 */
	private static int convertValidOffsetCaps(int newOffsetAscii) {

		int offset = newOffsetAscii;
		if (newOffsetAscii > CAPITAL_UPPER_RANGE) {
			int upperRangeOffset = newOffsetAscii % CAPITAL_UPPER_RANGE;
			while (newOffsetAscii > MAX_CHARACTER_POSITIVE_RANGE) {
				newOffsetAscii = upperRangeOffset % MAX_CHARACTER_POSITIVE_RANGE;

			}
			offset = newOffsetAscii % MAX_CHARACTER_POSITIVE_RANGE + SMALL_LOWER_RANGE - 1;
		} else if (newOffsetAscii < CAPITAL_LOWER_RANGE)
			offset = newOffsetAscii + MAX_CHARACTER_POSITIVE_RANGE;
		return offset;
	}

	/**
	 * @param inputString
	 * @return true or false based on String is valid or not.
	 */
	private static boolean validateString(String inputString) {
		if (inputString == null || inputString.isEmpty())
			return false;
		else
			return true;
	}

}