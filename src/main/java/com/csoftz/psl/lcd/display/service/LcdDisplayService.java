/*----------------------------------------------------------------------------*/
/* Source File:   LCDDISPLAYSERVICE.JAVA                                      */
/* Description:   Used to process and input for displaying numbers in LCD     */
/*                style.                                                      */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.20/2016                                                 */
/* Last Modified: Sep.21/2016                                                 */
/* Version:       1.2                                                         */
/* Copyright (c), 2016 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.20/2016 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.psl.lcd.display.service;

import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_EIGHT;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_FIVE;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_FOUR;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_NINE;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_ONE;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_SEVEN;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_SIX;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_THREE;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_TWO;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_ZERO;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.HORIZONTAL_SIGN;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.INPUT_SIZE_RANGE_END;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.INPUT_SIZE_RANGE_START;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.SPACE_SIGN;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.VERTICAL_SIGN;
import static com.csoftz.psl.lcd.display.consts.GlobalConstants.WILDCARD_SIGN;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.INPUT_DATA_EMPTY;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.INPUT_DATA_INVALID_CHARS;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.INPUT_SIZE_INVALID;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.INPUT_SIZE_NOT_IN_RANGE;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.NO_DATA_SUPPLIED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Used to process and input for displaying numbers in LCD style.
 * 
 * @since 1.8(JDK), Sep.20/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.2, Sep.21/2016
 */
@Service
public class LcdDisplayService implements ILcdDisplayService {

	/**
	 * Load template mask for digit number zero.
	 */
	@Value("${lcd.display.number.zero}")
	private String templateDigitZero = "";

	/**
	 * Load template mask for digit number one.
	 */
	@Value("${lcd.display.number.one}")
	private String templateDigitOne;

	/**
	 * Load template mask for digit number two.
	 */
	@Value("${lcd.display.number.two}")
	private String templateDigitTwo;

	/**
	 * Load template mask for digit number three.
	 */
	@Value("${lcd.display.number.three}")
	private String templateDigitThree;

	/**
	 * Load template mask for digit number four.
	 */
	@Value("${lcd.display.number.four}")
	private String templateDigitFour;

	/**
	 * Load template mask for digit number five.
	 */
	@Value("${lcd.display.number.five}")
	private String templateDigitFive;

	/**
	 * Load template mask for digit number six
	 */
	@Value("${lcd.display.number.six}")
	private String templateDigitSix;

	/**
	 * Load template mask for digit number seven.
	 */
	@Value("${lcd.display.number.seven}")
	private String templateDigitSeven;

	/**
	 * Load template mask for digit number eight.
	 */
	@Value("${lcd.display.number.eight}")
	private String templateDigitEight;

	/**
	 * Load template mask for digit number nine.
	 */
	@Value("${lcd.display.number.nine}")
	private String templateDigitNine;

	/**
	 * Holds an internal copy of input data with values to print.
	 */
	private String inputData;

	/**
	 * Compute how many rows must have the LCD screen with the 'size' or zoom
	 * factor.
	 */
	private Integer numRows;

	/**
	 * A map with numbers and sized accordingly. The zoom is done after size is
	 * greater than 1.
	 */
	private HashMap<String, List<String>> hmFinalTemplateDigits = new HashMap<>();

	/**
	 * Insert a specified number of characters in a starting position using a
	 * data as a basis to work on.
	 * 
	 * @param quantity
	 *            How many characters to insert
	 * @param offset
	 *            Where to insert characters
	 * @param templatePart
	 *            The data to work on.
	 * @param sign
	 *            The character to insert inside data
	 * @return
	 */
	private String insertNumberOfCharsInTemplate(final int quantity, int offset, final String templatePart,
			final String sign) {
		StringBuilder sb = new StringBuilder(templatePart);
		int j = 0;
		while (j < quantity) {
			sb.insert(offset, sign);
			j++;
		}
		return sb.toString();
	}

	/**
	 * Gets the template LCD digit and zooms to the given size.
	 * 
	 * @param size
	 *            Zoom factor.
	 * @param digitTemplate
	 *            The template LCD digit to use.
	 * @return A list of strings containing the zoomed LCD digit.
	 */
	private List<String> generateDigitFrom(int size, final String digitTemplate) {
		List<String> digitLines = new ArrayList<>();
		String[] templateLines = digitTemplate.split(",");
		int i = 0;
		while (i < templateLines.length) {
			String templatePart = templateLines[i].replace(WILDCARD_SIGN, SPACE_SIGN);
			if (size == 1) {
				digitLines.add(templatePart);
			} else {
				String buildPart = "";
				if (templatePart.contains(HORIZONTAL_SIGN)) {
					// Insert size - 1 characters after first character with the
					// character at second position in screen.
					buildPart = insertNumberOfCharsInTemplate(size - 1, 1, templatePart, HORIZONTAL_SIGN);
					digitLines.add(buildPart);
				} else {
					if (templatePart.contains(VERTICAL_SIGN)) {
						buildPart = insertNumberOfCharsInTemplate(size - 1, 1, templatePart, SPACE_SIGN);
						int times = 0;
						while (times < size) {
							digitLines.add(buildPart);
							times++;
						}
					} else {
						if (templatePart.contains(SPACE_SIGN)) {
							// Insert size - 1 characters after first character
							// with the character at second position in screen.
							buildPart = insertNumberOfCharsInTemplate(size - 1, 1, templatePart, SPACE_SIGN);
							digitLines.add(buildPart);
						}
					}
				}
			}
			i++;
		}
		return digitLines;
	}

	/**
	 * @see com.csoftz.psl.lcd.display.service.ILcdDisplayService#initialize(java.lang.String)
	 */
	public Boolean initialize(String data) throws Exception {
		if (data == null) {
			throw new Exception(NO_DATA_SUPPLIED);
		}
		if (data.equals("")) {
			throw new Exception("NO_DATA_SUPPLIED");
		}
		Boolean rslt = true;
		String[] dataValues = data.split(",");
		int size = -1;
		try {
			size = Integer.parseInt(dataValues[0]);
		} catch (Exception e) {
			throw new Exception(INPUT_SIZE_INVALID);
		}
		if (size == 0) {
			return false;
		}
		if (!(size >= INPUT_SIZE_RANGE_START && size <= INPUT_SIZE_RANGE_END)) {
			throw new Exception(INPUT_SIZE_NOT_IN_RANGE);
		}
		if (dataValues[1] == null || dataValues[1].equals("")) {
			throw new Exception(INPUT_DATA_EMPTY);
		}

		// Now it is time to validate the data input parameter that it contains
		// only
		// values of 0 through 9.
		for (char ch : dataValues[1].toCharArray()) {
			if (!(ch >= '0' && ch <= '9')) {
				throw new Exception(INPUT_DATA_INVALID_CHARS);
			}
		}

		hmFinalTemplateDigits.put(DIGIT_ZERO, generateDigitFrom(size, templateDigitZero));
		hmFinalTemplateDigits.put(DIGIT_ONE, generateDigitFrom(size, templateDigitOne));
		hmFinalTemplateDigits.put(DIGIT_TWO, generateDigitFrom(size, templateDigitTwo));
		hmFinalTemplateDigits.put(DIGIT_THREE, generateDigitFrom(size, templateDigitThree));
		hmFinalTemplateDigits.put(DIGIT_FOUR, generateDigitFrom(size, templateDigitFour));
		hmFinalTemplateDigits.put(DIGIT_FIVE, generateDigitFrom(size, templateDigitFive));
		hmFinalTemplateDigits.put(DIGIT_SIX, generateDigitFrom(size, templateDigitSix));
		hmFinalTemplateDigits.put(DIGIT_SEVEN, generateDigitFrom(size, templateDigitSeven));
		hmFinalTemplateDigits.put(DIGIT_EIGHT, generateDigitFrom(size, templateDigitEight));
		hmFinalTemplateDigits.put(DIGIT_NINE, generateDigitFrom(size, templateDigitNine));
		inputData = dataValues[1];
		numRows = hmFinalTemplateDigits.get(DIGIT_ZERO).size();
		return rslt;
	}

	/**
	 * @see com.csoftz.psl.lcd.display.service.ILcdDisplayService#printDigits()
	 */
	public List<String> printDigits() {
		HashMap<Integer, List<String>> hmActualDigit = new HashMap<>();
		List<String> rslt = new ArrayList<>();
		int digitPos = 0;
		for (char ch : inputData.toCharArray()) {
			List<String> hmValue = hmFinalTemplateDigits.get(String.valueOf(ch));
			hmActualDigit.put(digitPos, hmValue);
			digitPos++;
		}
		int numKeyValues = hmActualDigit.size();
		//System.out.println("numrows " + numRows);
		//System.out.println("numKeyValues " + numKeyValues);

		int rowCounter = 0;
		String linePart = "";
		while (rowCounter < numRows) {
			digitPos = 0;
			linePart = "";
			while (digitPos < numKeyValues) {
				List<String> hmValue = hmActualDigit.get(digitPos);
				linePart += hmValue.get(rowCounter);
				digitPos++;
			}
			rslt.add(linePart);
			rowCounter++;
		}
		return rslt;
	}
}
