/*----------------------------------------------------------------------------*/
/* Source File:   LCDDISPLAYAPPLICATION.JAVA                                  */
/* Description:   Main entry location for app.                                */
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

package com.csoftz.psl.lcd.display;

import static com.csoftz.psl.lcd.display.consts.GlobalConstants.APP_VERSION_NAME;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.APP_NO_PARAMS;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.APP_USAGE;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.FILE_CONTENTS;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.FILE_PROCESSING_ENDS;
import static com.csoftz.psl.lcd.display.consts.MessageConstants.FILE_PROCESSING_STARTS;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csoftz.psl.lcd.display.common.LoadTextLine;
import com.csoftz.psl.lcd.display.service.LcdDisplayService;

/**
 * Main entry location for app.
 * 
 * @since 1.8(JDK), Sep.19/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.3, Sep.21/2016
 */
@SpringBootApplication
public class LcdDisplayApplication {

	private static final Logger log = LoggerFactory.getLogger(LcdDisplayApplication.class);

	@Autowired
	private LcdDisplayService lcdDisplayService;

	/**
	 * Main entry for app.
	 * 
	 * @param args
	 *            Application commoand line parameters.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LcdDisplayApplication.class, args);
	}

	/**
	 * Execution of application.
	 * 
	 * @return the callback to run.
	 */
	@Bean
	public CommandLineRunner mainAppConsoleEntry() {
		return (args) -> {
			log.info(APP_VERSION_NAME);
			System.out.println(APP_VERSION_NAME);

			if (args.length < 1) {
				System.out.println(APP_NO_PARAMS);
				System.out.println(APP_USAGE);
				return;
			}

			LoadTextLine ltl = new LoadTextLine();
			List<String> mvtLines = ltl.readAll(args[1]);
			System.out.println(FILE_CONTENTS);
			mvtLines.forEach(s -> System.out.println(s));
			System.out.println(FILE_PROCESSING_STARTS);

			for (String l : mvtLines) {
				if (lcdDisplayService.initialize(l)) {
					List<String> printedLines = lcdDisplayService.printDigits();
					printedLines.forEach(System.out::println);
					System.out.println("  ");
				} else {
					// Line with contents '0,0' reached, process terminates.
					break;
				}
			}
			System.out.println(FILE_PROCESSING_ENDS);
		};
	}
}
