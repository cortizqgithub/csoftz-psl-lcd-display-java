/*----------------------------------------------------------------------------*/
/* Source File:   GLOBALCONSTANTS.JAVA                                        */
/* Description:   Global Constants used                                       */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.19/2016                                                 */
/* Last Modified: Sep.21/2016                                                 */
/* Version:       1.3                                                         */
/* Copyright (c), 2016 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.19/2016 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.psl.lcd.display.consts;

/**
 * Global application constants. Used as a static class access only. This way it
 * is assured that a change in the constant value is modified in one place.
 * 
 * @since 1.8(JDK), Sep.19/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.2, Sep.20/2016
 */
public class GlobalConstants {
	public static final String APP_VERSION_NAME = "LCD Display V1.0.0.17 Sep.21/2016";
	
	/* Display characters */
	public static final String HORIZONTAL_SIGN = "-";
	public static final String VERTICAL_SIGN = "|";
	public static final String SPACE_SIGN = " "; 
	public static final String WILDCARD_SIGN = "x";
	
	/* Rules */
	public static final int INPUT_SIZE_RANGE_START = 1;
	public static final int INPUT_SIZE_RANGE_END = 10;
}
