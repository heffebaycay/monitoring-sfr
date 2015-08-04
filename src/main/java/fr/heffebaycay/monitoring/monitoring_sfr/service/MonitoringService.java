package fr.heffebaycay.monitoring.monitoring_sfr.service;


import fr.heffebaycay.monitoring.monitoring_sfr.api.APIClient;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.VoIP;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip.GetInfoResponse;
import fr.heffebaycay.monitoring.monitoring_sfr.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private APIClient apiClient;

    public MonitoringService() {
        apiClient = new APIClient(Configuration.getNeufBoxHost(), Configuration.getNeufBoxUsername(), Configuration.getNeufBoxPassword());
        apiClient.authenticate();
    }

    public void printVoIPStatus() {

        VoIP voIPInterface = new VoIP(apiClient);

        GetInfoResponse getInfoResponse = voIPInterface.getInfo();

        if (getInfoResponse != null && getInfoResponse.getVoIP() != null) {
            GetInfoResponse.VoIPElement voIPInfo = getInfoResponse.getVoIP();

            System.out.printf("Statut du combiné: %1$s\n", voIPInfo.getHookStatus());
            System.out.printf("Statut du service VoIP: %1$s\n", voIPInfo.getStatus());
            System.out.printf("Infra VoIP: %1$s\n", voIPInfo.getInfra());
        } else if (getInfoResponse != null && getInfoResponse.getError() != null) {
            logger.error("Failed to get VoIP Status: {}", getInfoResponse.getError().getMsg());

        } else {
            logger.error("Failed to get VoIP Status");
        }

    }

}
