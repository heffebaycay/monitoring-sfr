package fr.heffebaycay.monitoring.monitoring_sfr.dao;

import fr.heffebaycay.monitoring.monitoring_sfr.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DaoManager {

    INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(DaoManager.class);

    private VoIPStatusDao voIPStatusDao;
    private Connection connection;

    DaoManager() {
        voIPStatusDao = new VoIPStatusDao();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + Configuration.getDatabaseName());
        } catch (SQLException e) {
            logger.error("Failed to get connection: {}", e);
            throw new RuntimeException("Failed to connect to DB", e);
        }
    }

    public VoIPStatusDao getVoIPStatusDao() {
        return voIPStatusDao;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        logger.debug("Closing DB connection");
        try {
            getConnection().close();
        } catch (SQLException e) {
            logger.error("Failed to close DB connection", e);
        }
    }

}
