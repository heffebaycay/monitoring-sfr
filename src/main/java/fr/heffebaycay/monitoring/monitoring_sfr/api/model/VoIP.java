package fr.heffebaycay.monitoring.monitoring_sfr.api.model;


import fr.heffebaycay.monitoring.monitoring_sfr.api.APIClient;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip.GetInfoRequest;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip.GetInfoResponse;

public class VoIP implements NeufboxInterface {

    protected APIClient mAPIClient;

    public VoIP(APIClient apiClient) {
        mAPIClient = apiClient;
    }

    public GetInfoResponse getInfo() {
        GetInfoRequest request = new GetInfoRequest();
        String strResponse = mAPIClient.sendRequest(request);

        if (strResponse != null) {
            GetInfoResponse response = GetInfoResponse.handleResponse(strResponse);
            return response;
        } else {
            return null;
        }
    }

}
