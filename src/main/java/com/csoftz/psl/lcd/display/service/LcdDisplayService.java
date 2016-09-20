/*----------------------------------------------------------------------------*/
/* Source File:   LCDDISPLAYSERVICE.JAVA                                      */
/* Description:   Used to process and input for displaying numbers in LCD     */
/*                style.                                                      */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.20/2016                                                 */
/* Last Modified: Sep.20/2016                                                 */
/* Version:       1.1                                                         */
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
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_SEVEN;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_SIX;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_THREE;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_TWO;
import static com.csoftz.psl.lcd.display.consts.DigitConstants.DIGIT_ZERO;

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
 * @version 1.1, Sep.20/2016
 */
@Service
public class LcdDisplayService implements ILcdDisplayService {

	/**
	 * Load template mask for digit number zero.
	 */
	@Value("${lcd.display.number.zero}")
	private String templateDigitZero;

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
	 * A map with numbers and sized accordingly. The zoom is done after size is
	 * greater than 1.
	 */
	private HashMap<String, List<String>> hmFinalTemplateDigits = new HashMap<>();

	/**
	 * All resizing is accomplished here.
	 * 
	 * @param size
	 *            Zoom Factor.
	 * @param templatePart
	 *            Part of LCD digit to zoom.
	 * @return Zoomed part.
	 */
	private String growDigitTemplatePart(int size, String templatePart) {
		String rslt = "";
		if (size == 1) {
			return templatePart;
		}
		return rslt;
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
	private List<String> generateDigitFrom(int size, String digitTemplate) {
		List<String> digitLines = new ArrayList<>();
		String[] templateLines = digitTemplate.split(",");
		int i = 0;
		while (i < templateLines.length) {
			digitLines.add(growDigitTemplatePart(size, templateLines[i]));
			i++;
		}
		return digitLines;
	}

	/**
	 * @see com.csoftz.psl.lcd.display.service.ILcdDisplayService#initialize(java.lang.String)
	 */
	public Boolean initialize(String data) throws Exception {
		if (data == null) {
			throw new Exception("No data supplied");
		}
		if (data.equals("")) {
			throw new Exception("No data supplied");
		}
		Boolean rslt = true;
		String[] dataValues = data.split(",");
		int size = -1;
		try {
			size = Integer.parseInt(dataValues[0]);
		} catch (Exception e) {
			throw new Exception("Value for input is not well formed");
		}
		if (size == 0) {
			return false;
		}
		if (!(size >= 1 && size <= 10)) {
			throw new Exception("Size input must be a value between 1 and 10");
		}

		hmFinalTemplateDigits.put(DIGIT_ZERO, generateDigitFrom(size, templateDigitOne));
		hmFinalTemplateDigits.put(DIGIT_TWO, generateDigitFrom(size, templateDigitTwo));
		hmFinalTemplateDigits.put(DIGIT_THREE, generateDigitFrom(size, templateDigitThree));
		hmFinalTemplateDigits.put(DIGIT_FOUR, generateDigitFrom(size, templateDigitFour));
		hmFinalTemplateDigits.put(DIGIT_FIVE, generateDigitFrom(size, templateDigitFive));
		hmFinalTemplateDigits.put(DIGIT_SIX, generateDigitFrom(size, templateDigitSix));
		hmFinalTemplateDigits.put(DIGIT_SEVEN, generateDigitFrom(size, templateDigitSeven));
		hmFinalTemplateDigits.put(DIGIT_EIGHT, generateDigitFrom(size, templateDigitEight));
		hmFinalTemplateDigits.put(DIGIT_NINE, generateDigitFrom(size, templateDigitNine));

		return rslt;
	}

	/**
	 * @see com.csoftz.psl.lcd.display.service.ILcdDisplayService#printDigits()
	 */
	public List<String> printDigits() {
		List<String> rslt = new ArrayList<>();
		return rslt;
	}
}
