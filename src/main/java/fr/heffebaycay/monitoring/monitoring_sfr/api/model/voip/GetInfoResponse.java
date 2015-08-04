package fr.heffebaycay.monitoring.monitoring_sfr.api.model.voip;

import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxError;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxResponse;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class GetInfoResponse extends NeufboxResponse {

    @Attribute(name = "stat")
    protected String status;

    @Attribute(name = "version")
    protected String version;

    @Element(name = "err", required = false)
    protected NeufboxError error;

    @Element(name = "voip", required = false)
    protected VoIPElement voIP;

    public static class VoIPElement {

        @Attribute(name = "status")
        protected String status;

        @Attribute(name = "infra")
        protected String infra;

        @Attribute(name = "hook_status")
        protected String hookStatus;

        @Attribute(name = "callhistory_active")
        protected String callHistoryActive;

        public String getStatus() {
            return status;
        }

        public String getInfra() {
            return infra;
        }

        public String getHookStatus() {
            return hookStatus;
        }

        public String getCallHistoryActive() {
            return callHistoryActive;
        }
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }

    public NeufboxError getError() {
        return error;
    }

    public VoIPElement getVoIP() {
        return voIP;
    }

    public static GetInfoResponse handleResponse(String response) {
        return deserialize(GetInfoResponse.class, response);
    }
}
