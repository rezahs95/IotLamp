package ir.altontelecom.iotlamp.webservice.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Parameters")
    private Parameters parameters;
    @SerializedName("Status")
    private Status status;

    public LoginResponse() {
    }

    public LoginResponse(Parameters parameters, Status status) {
        this.parameters = parameters;
        this.status = status;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Parameters {
        @SerializedName("SessionKey")
        private String sessionKey;

        public Parameters() {
        }

        public Parameters(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }
    }

    public static class Status {
        @SerializedName("Code")
        private String code;
        @SerializedName("Description")
        private String description;

        public Status() {
        }

        public Status(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
