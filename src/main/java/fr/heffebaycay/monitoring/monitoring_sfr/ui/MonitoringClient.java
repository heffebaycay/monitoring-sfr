package fr.heffebaycay.monitoring.monitoring_sfr.ui;


import fr.heffebaycay.monitoring.monitoring_sfr.config.Configuration;
import fr.heffebaycay.monitoring.monitoring_sfr.service.MonitoringService;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringClient {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringClient.class);

    public static void main(String[] args) {

        // Loading configuration properties
        boolean configSuccess = Configuration.load();

        if (configSuccess) {
            logger.debug("Successfully loaded application configuration");
        } else {
            logger.error("Failed to load application configuration");
        }

        if (args.length > 0 && "-migrate".equals(args[0])) {
            logger.info("Initializing migration mode");
            migrate();
        } else {
            logger.info("Initializing monitoring service");
            MonitoringService monitoringService = new MonitoringService();
            monitoringService.processMonitoring();
        }

    }

    private static void migrate() {
        Flyway flyway = new Flyway();

        flyway.setDataSource("jdbc:sqlite:" + Configuration.getDatabaseName(), "", "");

        flyway.migrate();
    }

}
