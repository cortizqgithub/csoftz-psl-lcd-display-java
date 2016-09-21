/*----------------------------------------------------------------------------*/
/* Source File:   LCDDISPLAYAPPLICATION.JAVA                                  */
/* Description:   Main entry location for app.                                */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Sep.19/2016                                                 */
/* Last Modified: Sep.20/2016                                                 */
/* Version:       1.2                                                         */
/* Copyright (c), 2016 CSoftZ                                                 */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Sep.19/2016 COQ  File created.
 -----------------------------------------------------------------------------*/

package com.csoftz.psl.lcd.display;

import static com.csoftz.psl.lcd.display.consts.GlobalConstants.APP_VERSION_NAME;

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
 * @version 1.1, Sep.19/2016
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
				System.out.println("No parameters supplied.");
				System.out.println("Usage: supply a path to a file to process");
				return;
			}

			try {
				LoadTextLine ltl = new LoadTextLine();
				List<String> mvtLines = ltl.readAll(args[1]);
				System.out.println("File contentes are:");
				mvtLines.forEach(s -> System.out.println(s));
				System.out.println("Processing file starts");

				for (String l : mvtLines) {
					try {
						if (lcdDisplayService.initialize(l)) {
							List<String> printedLines = lcdDisplayService.printDigits();
							printedLines.forEach(System.out::println);
							System.out.println("  ");
							System.out.println("  ");
						}
						else {
							break;
						}
					} catch (Exception e) {
						System.out.println("Input string [" + l + "] is not well formed, skipped.");
						System.out.println(e.getMessage());
					}
				}
				System.out.println("Processing file ends");
			} catch (Exception e) {
				System.out.println("Missing file at " + args[0]);
			}

		};
	}
}