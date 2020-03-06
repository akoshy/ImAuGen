package com.encryption;

public class EmbedExtractUtil {
	
	public static byte[] encodeText(byte[] data, byte[] addition, int offset){
		// check that the data + offset will fit in the image
		if (addition.length + offset > data.length) {
			throw new IllegalArgumentException("File not long enough!");
		}
		// loop through each addition byte
		for (int i = 0; i < addition.length; ++i) {
			// loop through the 8 bits of each byte
			int add = addition[i];
			for (int bit = 7; bit >= 0; --bit, ++offset) { // ensure the new offset value carries on through both loops
				// assign an integer to b, shifted by bit spaces AND 1
				// a single bit of the current byte
				int b = (add >>> bit) & 1;
				// assign the bit by taking: [(previous byte value) AND 0xfe] OR bit to add
				// changes the last bit of the byte in the image to be the bit of addition
				data[offset] = (byte) ((data[offset] & 0xFE) | b);
			}

		}

		return data;

	}

	public static byte[] decodeText(byte[] data){
		int length = 0;
		int offset = 32;
		// loop through 32 bytes of data to determine text length
		for (int i = 0; i < 32; ++i) { // i=24 will also work, as only the 4th byte contains real data
			length = (length << 1) | (data[i] & 1);
		}
		byte[] result = new byte[length];
		// loop through each byte of text
		for (int b = 0; b < result.length; ++b){
			// loop through each bit within a byte of text
			for (int i = 0; i < 8; ++i, ++offset){
				// assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
				result[b] = (byte) ((result[b] << 1) | (data[offset] & 1));
			}
		}
		return result;

	}

	public static byte[] bitConversion(int i) {

		return (new byte[] { 0, 0, 0, (byte) (i & 0x000000FF) });

	}
}
