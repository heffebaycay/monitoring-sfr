package fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip;

import fr.heffebaycay.monitoring.monitoring_sfr.api.HttpMethod;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxRequest;

import java.util.Map;

public class GetInfoRequest extends NeufboxRequest {

    private static final String METHOD_NAME = "voip.getInfo";
    private static final HttpMethod HTTP_METHOD = HttpMethod.GET;
    private static final boolean IS_PRIVATE = true;

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

    @Override
    public Map<String, String> getParams() {
        return null;
    }

}
