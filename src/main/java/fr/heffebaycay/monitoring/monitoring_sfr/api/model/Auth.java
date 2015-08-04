package fr.heffebaycay.monitoring.monitoring_sfr.api.model;


import fr.heffebaycay.monitoring.monitoring_sfr.api.APIClient;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.CheckTokenRequest;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.CheckTokenResponse;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.GetTokenRequest;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.GetTokenResponse;

public class Auth implements NeufboxInterface {

    protected APIClient mAPIClient;

    public Auth(APIClient apiClient) {
        mAPIClient = apiClient;
    }

    public GetTokenResponse getToken() {

        GetTokenRequest request = new GetTokenRequest();
        String strResponse = mAPIClient.sendRequest(request);

        if (strResponse != null) {
            GetTokenResponse response = GetTokenResponse.handleResponse(strResponse);
            return response;
        } else {
            return null;
        }
    }

    public CheckTokenResponse checkToken(String hash) {
        CheckTokenRequest request = new CheckTokenRequest(hash);
        String strResponse = mAPIClient.sendRequest(request);

        if (strResponse != null) {
            CheckTokenResponse response = CheckTokenResponse.handleResponse(strResponse);
            return response;
        } else {
            return null;
        }
    }


}
