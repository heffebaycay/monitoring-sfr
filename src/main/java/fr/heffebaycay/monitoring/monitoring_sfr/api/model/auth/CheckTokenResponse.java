package fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth;

import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxError;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxResponse;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class CheckTokenResponse extends NeufboxResponse {

    @Attribute(name = "stat")
    protected String status;

    @Attribute(name = "version")
    protected String version;

    @Element(name = "err", required = false)
    protected NeufboxError error;

    @Element(name = "auth", required = false)
    protected GetTokenResponse.AuthElement auth;


    public NeufboxError getError() {
        return error;
    }

    public GetTokenResponse.AuthElement getAuth() {
        return auth;
    }

    public static CheckTokenResponse handleResponse(String response) {
        return deserialize(CheckTokenResponse.class, response);
    }

}
