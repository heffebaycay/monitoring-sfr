package fr.heffebaycay.monitoring.monitoring_sfr.service;


import fr.heffebaycay.monitoring.monitoring_sfr.api.APIClient;
import fr.heffebaycay.monitoring.monitoring_sfr.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    public void testApi() {

        APIClient apiClient = new APIClient("192.168.1.1", Configuration.getNeufBoxUsername(), Configuration.getNeufBoxPassword());

        apiClient.authenticate();

    }


}
