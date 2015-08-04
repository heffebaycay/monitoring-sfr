package fr.heffebaycay.monitoring.monitoring_sfr.api.model;


import fr.heffebaycay.monitoring.monitoring_sfr.api.HttpMethod;

import java.util.Map;

public abstract class NeufboxRequest {

    public abstract boolean isPrivate();

    public abstract HttpMethod getHttpMethod();

    public abstract String getMethodName();

    public abstract Map<String, String> getParams();

}
