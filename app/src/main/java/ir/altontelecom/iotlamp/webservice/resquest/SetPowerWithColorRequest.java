package ir.altontelecom.iotlamp.webservice.resquest;

import com.google.gson.annotations.SerializedName;

public class SetPowerWithColorRequest {
    @SerializedName("Identity")
    private Identity identity;
    @SerializedName("Parameters")
    private Parameters parameters;

    public SetPowerWithColorRequest() {
    }

    public SetPowerWithColorRequest(Identity identity, Parameters parameters) {
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
        @SerializedName("Red")
        private Integer red;
        @SerializedName("Green")
        private Integer green;
        @SerializedName("Blue")
        private Integer blue;
        @SerializedName("FadeTime")
        private Integer fadeTime;
        @SerializedName("Delay")
        private Integer delay;
        @SerializedName("Power")
        private Boolean power;

        public Parameters() {
        }

        public Parameters(Integer red, Integer green, Integer blue, Integer fadeTime, Integer
                delay, Boolean power) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.fadeTime = fadeTime;
            this.delay = delay;
            this.power = power;
        }

        public Integer getRed() {
            return red;
        }

        public void setRed(Integer red) {
            this.red = red;
        }

        public Integer getGreen() {
            return green;
        }

        public void setGreen(Integer green) {
            this.green = green;
        }

        public Integer getBlue() {
            return blue;
        }

        public void setBlue(Integer blue) {
            this.blue = blue;
        }

        public Integer getFadeTime() {
            return fadeTime;
        }

        public void setFadeTime(Integer fadeTime) {
            this.fadeTime = fadeTime;
        }

        public Integer getDelay() {
            return delay;
        }

        public void setDelay(Integer delay) {
            this.delay = delay;
        }

        public Boolean getPower() {
            return power;
        }

        public void setPower(Boolean power) {
            this.power = power;
        }
    }
}
