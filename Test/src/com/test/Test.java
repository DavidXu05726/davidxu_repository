/**
 * 
 */
package com.test;

import java.io.UnsupportedEncodingException;

/**
 * @author ISSUER
 *
 */
public class Test {

	public static void duplicateNumber(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if ((array[i] == array[j]) && (i != j)) {
					System.out.println("ÖØ¸´ÔªËØ : " + array[j]);
				}
			}
		}
	}

	public static void exchange(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
		System.out.println("x =" + x);
		System.out.println("y=" + y);

	}

	public static int getUU() {
		return 0;
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		int[] array = {1,3,3,5,5,7,8};
		for (int i = 0; i < array.length - 1; i++) {
			if((array[i]==array[i+1]) && (i<(array.length-2))) {
				System.out.println(array[i]);
				continue;
			}else {
				continue;
			}
		}
		//duplicateNumber(array);
		

	}
}