package ir.altontelecom.iotlamp.webservice.resquest;

import com.google.gson.annotations.SerializedName;

public class GetStateRequest {
    @SerializedName("Identity")
    private Identity identity;

    public GetStateRequest() {
    }

    public GetStateRequest(Identity identity) {
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
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
}
