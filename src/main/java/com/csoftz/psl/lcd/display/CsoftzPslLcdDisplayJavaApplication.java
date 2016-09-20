/*----------------------------------------------------------------------------*/
/* Source File:   CSOFTZPSLLCDDISPLAYJAVAAPPLICATION.JAVA                     */
/* Description:   Main entry location for app.                                */
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

package com.csoftz.psl.lcd.display;

import static com.csoftz.psl.lcd.display.consts.GlobalConstants.APP_VERSION_NAME;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csoftz.psl.lcd.display.common.LoadTextLine;

/**
 * Main entry location for app.
 * 
 * @since 1.8(JDK), Sep.19/2016
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Sep.19/2016
 */

@SpringBootApplication
public class CsoftzPslLcdDisplayJavaApplication {

	private static final Logger log = LoggerFactory.getLogger(CsoftzPslLcdDisplayJavaApplication.class);

	/**
	 * Main entry for app.
	 * 
	 * @param args
	 *            Application commoand line parameters.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CsoftzPslLcdDisplayJavaApplication.class, args);
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

			/*if (args.length < 2) {
				System.out.println("No parameters supplied.");
				System.out.println("First parameter is Movement file");
				System.out.println("Second parameter is Threats file");
				return;
			}*/
			
			/*while (true) {

				System.out.print("Enter something : ");
				String input = System.console().readLine();

				if ("q".equals(input)) {
					System.out.println("Exit!");
					System.exit(0);
				}

				System.out.println("input : " + input);
				System.out.println("-----------\n");
			}*/
			LoadTextLine ltl = new LoadTextLine();
			List<String> mvtLines = ltl.readAll("samples/lines.txt");
			mvtLines.forEach(s -> System.out.println(s));
		};
	}
}
