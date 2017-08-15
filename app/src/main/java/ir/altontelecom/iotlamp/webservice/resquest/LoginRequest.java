package ir.altontelecom.iotlamp.webservice.resquest;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("Parameters")
    private Parameters parameters;

    public LoginRequest() {
    }

    public LoginRequest(Parameters parameters) {
        this.parameters = parameters;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public static class Parameters {
        @SerializedName("Username")
        private String username;
        @SerializedName("Password")
        private String password;

        public Parameters() {
        }

        public Parameters(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
