package fr.heffebaycay.monitoring.monitoring_sfr.service;


public enum ServiceManager {

    INSTANCE;

    private MonitoringService monitoringService;

    private ServiceManager() {
        monitoringService = new MonitoringService();
    }

    public MonitoringService getMonitoringService() {
        return monitoringService;
    }


}
