/*----------------------------------------------------------------------------*/
/* Source File:   MESSAGECONSTANTS.JAVA                                       */
/* Description:   Meesage Constants used                                      */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.21/2016                                                 */
/* Last Modified: Sep.21/2016                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2016 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.21/2016 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.psl.lcd.display.consts;

/**
 * Message application constants. Used as a static class access only. This way
 * it is assured that a change in the constant value is modified in one place.
 * 
 * @since 1.8(JDK), Sep.19/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.2, Sep.20/2016
 */

public class MessageConstants {
	public static final String APP_NO_PARAMS = "No parameters supplied.";
	public static final String APP_USAGE = "Usage: supply a path to a file to process";
	
	public static final String FILE_CONTENTS = "File contentes are:";
	public static final String FILE_PROCESSING_STARTS = "Processing file starts";
	public static final String FILE_PROCESSING_ENDS = "Processing file ends";
	public static final String FILE_MISSING_PATH = "Missing file at ";
	
	public static final String NO_DATA_SUPPLIED = "No data supplied";
	public static final String INPUT_SIZE_INVALID = "Value for input size is not well formed";
	public static final String INPUT_DATA_EMPTY = "Data input is empty, cannot process";
	public static final String INPUT_SIZE_NOT_IN_RANGE="Size input must be a value between 1 and 10";
	public static final String INPUT_DATA_INVALID_CHARS = "Data input does not contain characters [0..9]";
}
