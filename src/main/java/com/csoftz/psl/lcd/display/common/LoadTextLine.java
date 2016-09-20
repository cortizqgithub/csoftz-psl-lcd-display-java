/*----------------------------------------------------------------------------*/
/* Source File:   LOADTEXTLINE.JAVA                                           */
/* Description:   Helper Class to load string lines from a given file.        */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.19/2016                                                 */
/* Last Modified: Sep.19/2016                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2016 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.19/2016 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.psl.lcd.display.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Helper Class to load string lines from a given file.
 * 
 * @since 1.8(JDK), Sep.19/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Sep.19/2016
 */
public class LoadTextLine {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * Load all the contents in a filename and returns in a string list for
	 * further processing.
	 * 
	 * @return List of lines as string.
	 */
	public List<String> readAll(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path, ENCODING);
	}
}