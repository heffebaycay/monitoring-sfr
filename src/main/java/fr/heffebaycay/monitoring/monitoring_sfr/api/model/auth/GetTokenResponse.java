package fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth;

import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxError;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxResponse;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class GetTokenResponse extends NeufboxResponse {

    @Attribute(name = "stat")
    protected String status;

    @Attribute(name = "version")
    protected String version;

    @Element(name = "err", required = false)
    protected NeufboxError error;

    @Element(name = "auth", required = false)
    protected AuthElement auth;

    public static class AuthElement {

        @Attribute(name = "token")
        protected String token;

        @Attribute(name = "method", required = false)
        protected String method;

        public String getToken() {
            return token;
        }

        public String getMethod() {
            return method;
        }
    }

    public AuthElement getAuth() {
        return auth;
    }

    public static GetTokenResponse handleResponse(String response) {
        return deserialize(GetTokenResponse.class, response);
    }

}
