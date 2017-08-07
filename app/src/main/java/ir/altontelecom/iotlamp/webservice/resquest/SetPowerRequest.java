package ir.altontelecom.iotlamp.webservice.resquest;

import com.google.gson.annotations.SerializedName;

public class SetPowerRequest {
    @SerializedName("Identity")
    private Identity identity;
    @SerializedName("Parameters")
    private Parameters parameters;

    public SetPowerRequest() {
    }

    public SetPowerRequest(Identity identity, Parameters parameters) {
        this.identity = identity;
        this.parameters = parameters;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public static class Identity {
        @SerializedName("SessionKey")
        private String sessionKey;

        public Identity() {
        }

        public Identity(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }
    }

    public static class Parameters {
        @SerializedName("Power")
        private Boolean power;

        public Parameters() {
        }

        public Parameters(Boolean power) {
            this.power = power;
        }

        public Boolean getPower() {
            return power;
        }

        public void setPower(Boolean power) {
            this.power = power;
        }
    }
}