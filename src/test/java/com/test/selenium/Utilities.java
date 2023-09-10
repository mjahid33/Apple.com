package com.test.selenium;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class Utilities {

	/**
	 * Generate random number
	 * 
	 * @param aStart
	 *            : start length of random integer
	 * @param aEnd
	 *            : end length of random number like 10-11 it will generate
	 *            random number between 10-11 digits
	 * @return
	 */
	public static int getRandomInteger(int aStart, int aEnd) {
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		return (int) (fraction + aStart);
	}

	/**
	 * Generate random string
	 * 
	 * @param len
	 *            : length of random string
	 * @return
	 */
	public static String randomString(int len) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	/**
	 * Get absolute path
	 */
	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	/**
	 * @param file
	 * @return
	 */
	public static String getFileName(String file) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		Calendar cal = Calendar.getInstance();
		String fileName = file + dateFormat.format(cal.getTime());
		return fileName;
	}

	/**
	 * Get absolute path
	 */
	public static String getPathUpload() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");
		return path;
	}

	/**
	 * Get time stamp
	 * 
	 * @return
	 */
	public static long getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}

	/**
	 * Get time stamp
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String[] splitStringWithNewLine(String str) throws Exception {
		if (StringUtils.isBlank(str)) {
			throw new Exception("Please provide valid string");
		}
		return str.split("\\r?\\n");
	}

	/**
	 * Extract numbers from string
	 * @param string : string with number
	 * @return
	 */
	public static int getNumberFromString(String string) {
		return Integer.parseInt(string.replaceAll("[^0-9]", ""));
	}
}
