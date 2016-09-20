/*----------------------------------------------------------------------------*/
/* Source File:   ILCDDISPLAYSERVICE.JAVA                                     */
/* Description:   Used to process and input for displaying numbers in LCD     */
/*                style. Defines the contract.                                */
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

import java.util.List;

/**
 * Used to process and input for displaying numbers in LCD style. Defines the
 * contract.
 * 
 * @since 1.8(JDK), Sep.20/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Sep.20/2016
 */
public interface ILcdDisplayService {
	/**
	 * Assign data to process.
	 * @param data Input data to process. It is in the format of 'n,mn...z' where n, m, z are numbers, and example follows '1,123'.
	 * @return True if process can continue. It halts when input is '0,0', and false is returned.
	 * @throws Exception
	 */
	public Boolean initialize(String data) throws Exception;
	
	/**A call to 'initialize' must be done in order for this one to succeed.
	 * @return A list of strings containing the the requested digits to be displayed.
	 */
	public List<String> printDigits();
}
