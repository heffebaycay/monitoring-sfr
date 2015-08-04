package fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth;


import fr.heffebaycay.monitoring.monitoring_sfr.api.HttpMethod;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxRequest;

import java.util.HashMap;
import java.util.Map;

public class CheckTokenRequest extends NeufboxRequest {

    private static final String METHOD_NAME = "auth.checkToken";
    private static final HttpMethod HTTP_METHOD = HttpMethod.GET;
    private static final boolean IS_PRIVATE = false;

    @Override
    public boolean isPrivate() {
        return IS_PRIVATE;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HTTP_METHOD;
    }

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    protected String mHash;

    public CheckTokenRequest(String hash) {
        mHash = hash;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String hash) {
        this.mHash = hash;
    }

    @Override
    public Map<String, String> getParams() {

        if (mHash == null) {
            return null;
        }

        Map<String, String> params = new HashMap<>();

        params.put("hash", mHash);

        return params;

    }
}
