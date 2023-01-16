/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/**
 * This class is for the Logger of the test-API.k6.io
 *
 * @author Libertad Tolaba
 * @version 1.0
 */
public final class ATLogger {
    private static final String PROPERTIES_FILE = "log4j.properties";
    private final Logger log = Logger.getLogger("ATLogger");
    public static final ATLogger INSTANCE = new ATLogger();
    private ATLogger() {
        try {
            LogManager.getLogManager()
                    .readConfiguration(new FileInputStream(PROPERTIES_FILE));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public static ATLogger getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return log;
    }
}