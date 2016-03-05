package fr.heffebaycay.monitoring.monitoring_sfr.service;


import fr.heffebaycay.monitoring.monitoring_sfr.api.APIClient;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.VoIP;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip.GetInfoResponse;
import fr.heffebaycay.monitoring.monitoring_sfr.config.Configuration;
import fr.heffebaycay.monitoring.monitoring_sfr.dao.DaoManager;
import fr.heffebaycay.monitoring.monitoring_sfr.dao.VoIPStatusDao;
import fr.heffebaycay.monitoring.monitoring_sfr.model.VoIPStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private APIClient apiClient;
    private VoIPStatusDao voIPStatusDao;

    public MonitoringService() {
        voIPStatusDao = DaoManager.INSTANCE.getVoIPStatusDao();
        apiClient = new APIClient(Configuration.getNeufBoxHost(), Configuration.getNeufBoxUsername(), Configuration.getNeufBoxPassword());
        apiClient.authenticate();
    }

    public void processMonitoring() {
        Connection connection = DaoManager.INSTANCE.getConnection();
        monitorVoIP(connection);
        DaoManager.INSTANCE.closeConnection();
    }

    private void monitorVoIP(Connection conn) {
        logger.debug("Processing VoIP monitoring");
        VoIP voIPInterface = new VoIP(apiClient);
        GetInfoResponse info = voIPInterface.getInfo();
        if (info != null && info.getVoIP() != null) {
            GetInfoResponse.VoIPElement voIPInfo = info.getVoIP();
            voIPStatusDao.save(VoIPStatus.fromDto(voIPInfo), conn);
        }
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
