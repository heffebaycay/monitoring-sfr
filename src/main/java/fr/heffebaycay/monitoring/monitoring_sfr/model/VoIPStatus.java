package fr.heffebaycay.monitoring.monitoring_sfr.model;

import fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip.GetInfoResponse;

import java.time.LocalDateTime;

public class VoIPStatus {

    private int id;
    private String status;
    private String infra;
    private String hookStatus;
    private String callHistoryActive;
    private String dateRecorded;

    public VoIPStatus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfra() {
        return infra;
    }

    public void setInfra(String infra) {
        this.infra = infra;
    }

    public String getHookStatus() {
        return hookStatus;
    }

    public void setHookStatus(String hookStatus) {
        this.hookStatus = hookStatus;
    }

    public String getCallHistoryActive() {
        return callHistoryActive;
    }

    public void setCallHistoryActive(String callHistoryActive) {
        this.callHistoryActive = callHistoryActive;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public static VoIPStatus fromDto(GetInfoResponse.VoIPElement element) {
        VoIPStatus status = new VoIPStatus();
        status.setInfra(element.getInfra());
        status.setStatus(element.getStatus());
        status.setHookStatus(element.getHookStatus());
        status.setCallHistoryActive(element.getCallHistoryActive());
        status.setDateRecorded(LocalDateTime.now().toString());

        return status;
    }
}
