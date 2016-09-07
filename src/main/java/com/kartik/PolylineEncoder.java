package com.kartik;

/**
 *
 *	@author KARTIK
 * Implements the Polyline Algorithm defined here
 * {@literal https://developers.google.com/maps/documentation/utilities/polylinealgorithm}
 */
public class PolylineEncoder {
	/**
	 * This function takes the decimal value and multiply by 1e5, rounding the result
	 *  
	 * @param pointToRound the number whose rounded value is to be obtained
	 * @return the round up value of the point multiplied by 1e5
	 *
	 */
	public int roundUp(double pointToRound) {
		int point = (int)Math.round(pointToRound * Math.pow(10,5));
		return point;
	}

	/**
	 * Obtains the Binary equivalent of a given Integer
	 * 
	 * @param pointToEncode the latutide/longitude value with decimal part rounded up
	 * @return binary equivalent of given of the point
	 */
	public String numberToBinary(int pointToEncode) {
		return Integer.toBinaryString(pointToEncode);
	}

	/**
	 * Left shifts the binary value by 1 bit
	 * 
	 * @param pointToEncode binary value that is to be left-shift
	 * @return left-shifted binary value
	 */
	public String leftShiftBinary(String pointToEncode) {
		String shiftedBinary = "";
		for(int i = 0; i < pointToEncode.length() - 1; i++) {
			shiftedBinary = shiftedBinary + pointToEncode.charAt(i+1);
		}
		shiftedBinary = "1" + shiftedBinary + "0";
		//		System.out.println(shiftedBinary);
		return shiftedBinary;
	}

	/**
	 * Inverts the bits
	 * 
	 * @param pointToEncode the point whose bits are to be inverted
	 * @return the inverted bits of the point
	 */
	public String invert(String pointToEncode) {
		String invertedString ="";
		for (int i = 0; i < pointToEncode.length(); i++) {
			if (pointToEncode.charAt(i) == '1') {
				invertedString = invertedString + "0";
			} else {
				invertedString = invertedString + "1";
			}	
		}
		return invertedString;
	}

	/**
	 * This function is used to form the chunks
	 * @param pointToEncode the binary string to be transformed into chunks of 5 bits each
	 * @return an array of binary string of 5 bits each
	 */
	public String[] getChunks(String pointToEncode) {
		int index = 0, var;
		double temp = (pointToEncode.length() / 5.0);
		int size = (int)Math.ceil(temp);
		String[] chunks = new String[size];
		for (int i = pointToEncode.length() - 1; i >= 0 && index < size; i = i-5) {
			var = 4;
			chunks[index] = "00000";
			StringBuilder str = new StringBuilder(chunks[index]);
			for(int j = i; j > i - 5 && j >= 0; j--) {
				str.setCharAt(var, pointToEncode.charAt(j));
				var = var - 1;
			}
			chunks[index] = str.toString();
			index++;
		}
		return chunks;	
	}

	/**
	 * This function will OR each value(except the last one) with 0x20 if another bit chunk follows:
	 * @param chunks binary string array of chunks
	 * @return an array containing the or-ed value of each element 
	 */
	public String[] orChunks(String[] chunks) {
		int index = chunks.length - 1;
		if (index == 0) {
			return chunks;
		}
		while(chunks[index].equals("00000")) {
			index = index - 1; 
		}
		StringBuilder str = new StringBuilder("100000");
		String[] resultChunks = new String [index + 1];
		for(int i = 0; i < index; i++) {
			StringBuilder str1 = new StringBuilder(chunks[i]);
			str1.insert(0,'0');
			for (int j = 0; j < 6; j++) {
				if (str.charAt(j) == str1.charAt(j)){
					str1.setCharAt(j, '0');
				} else {
					str1.setCharAt(j, '1');
				}
			}
			resultChunks[i] = str1.toString();
		}
		resultChunks[index] = chunks[index];
		return resultChunks;
	}

	/**
	 * Converts each chunk to its corresponding decimal value
	 * 
	 * @param oredchunks an array of chunks that were or-ed
	 * @return an array of decimal value of each chunk
	 */
	public int[] decimalConversion(String[] oredchunks) {
		int[] decimalChunks = new int[oredchunks.length];
		for (int i = 0; i < oredchunks.length; i++) {
			decimalChunks[i] = Integer.parseInt(oredchunks[i], 2);
		}
		return decimalChunks;
	}

	/**
	 * Add 63 to each each element of the decimalValue array
	 * 
	 * @param decimalValue an array of decimal values
	 * @return an array whose each element was added 63
	 */
	public int[] addSixtyThree(int[] decimalValue) {
		int[] temp = new int[decimalValue.length];
		for(int i = 0; i < decimalValue.length; i++) {
			temp[i] = decimalValue[i] + 63;
		}
		return temp;
	}

	/**
	 * Obtain ASCII value of given integer
	 * 
	 * @param values an array of integer values
	 * @return a String containing the ASCII values
	 */
	public String convertToAscii(int[] values) {
		String encodedString="";
		for (int i = 0; i < values.length; i++) {
			encodedString = encodedString + ((char)values[i]);
		}
		return encodedString;
	}

	/**
	 * This function will start the conversion process by calling other necessary functions
	 * 
	 * @param pointToEncode
	 * @return the encoded polyline
	 */
	public String startConversionProcess(int pointToEncode) {
		String binaryPoint = numberToBinary(pointToEncode);
		String leftShift = leftShiftBinary(binaryPoint);
		String invertPoint = "";
		String encodedString;
		String[] chunks, oredChunks;
		int[] decimalValue, add63;
		if (pointToEncode < 0) {
			invertPoint = invert(leftShift);
			//			System.out.println("1...\n" + invertPoint + "\n2...");
		} else {
			invertPoint = leftShift;
		}
		chunks = getChunks(invertPoint);
		oredChunks = orChunks(chunks);
		decimalValue = decimalConversion(oredChunks);
		add63 = addSixtyThree(decimalValue);
		encodedString = convertToAscii(add63);
		return encodedString;
	}

	/**
	 * This function produces the encoded polyline for the given pairs of Latitudes
	 *  and Longitudes
	 * 
	 * @param gpsPoints an array of objects of type Gps that has the Lat-Long values
	 * @return the encoded Polyline
	 */
	public String encodePolyline(Gps[] gpsPoints) {
		String encodedPolyline = "";
		int i, pointToEncode;
		for (i = 0; i < gpsPoints.length; i++) {
			if (i == 0) {
				pointToEncode = roundUp(gpsPoints[i].getLatitude());
			} else {
				pointToEncode = roundUp(gpsPoints[i].getLatitude() - gpsPoints[i - 1].getLatitude());
			}
			encodedPolyline = encodedPolyline + startConversionProcess(pointToEncode);
			if (i == 0) {
				pointToEncode = roundUp(gpsPoints[i].getLongitude());
			} else {
				pointToEncode = roundUp(gpsPoints[i].getLongitude() - gpsPoints[i - 1].getLongitude());
			}
			encodedPolyline = encodedPolyline + startConversionProcess(pointToEncode);
		}
		return encodedPolyline;
	}
}
