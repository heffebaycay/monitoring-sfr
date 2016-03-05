package fr.heffebaycay.monitoring.monitoring_sfr.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static final String CONFIG_PATH = "./config.properties";
    private static final String CONFIG_NEUFBOX_USERNAME = "neufbox.username";
    private static final String CONFIG_NEUFBOX_PASSWORD = "neufbox.password";
    private static final String CONFIG_NEUFBOX_HOST = "neufbox.host";
    private static final String CONFIG_DATABASE_NAME = "database.name";

    protected static String neufBoxUsername;
    protected static String neufBoxPassword;
    protected static String neufBoxHost;
    protected static String databaseName;

    public static String getNeufBoxUsername() {
        return neufBoxUsername;
    }

    public static String getNeufBoxPassword() {
        return neufBoxPassword;
    }

    public static String getNeufBoxHost() {
        return neufBoxHost;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static boolean load() {
        Properties configProperties = new Properties();

        try {
            InputStream fis = new FileInputStream(CONFIG_PATH);
            configProperties.load(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            logger.error("Failed to find config file: {}", e);
            return false;
        } catch (IOException e) {
            logger.error("Failed to load or close config file: {}", e);
            return false;
        }

        neufBoxUsername = configProperties.getProperty(CONFIG_NEUFBOX_USERNAME);
        neufBoxPassword = configProperties.getProperty(CONFIG_NEUFBOX_PASSWORD);
        neufBoxHost = configProperties.getProperty(CONFIG_NEUFBOX_HOST);
        databaseName = configProperties.getProperty(CONFIG_DATABASE_NAME);

        return true;
    }

}
