package ir.altontelecom.iotlamp.webservice.response;

import com.google.gson.annotations.SerializedName;

public class GetStateResponse {
    @SerializedName("Parameters")
    private Parameters parameters;
    @SerializedName("Status")
    private Status status;

    public GetStateResponse() {
    }

    public GetStateResponse(Parameters parameters, Status status) {
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
        @SerializedName("Red")
        private Integer red;
        @SerializedName("Green")
        private Integer green;
        @SerializedName("Blue")
        private Integer blue;
        @SerializedName("FadeTime")
        private Integer fadeTime;
        @SerializedName("Power")
        private Boolean power;

        public Parameters() {
        }

        public Parameters(Integer red, Integer green, Integer blue, Integer fadeTime, Boolean
                power) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.fadeTime = fadeTime;
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

        public Boolean getPower() {
            return power;
        }

        public void setPower(Boolean power) {
            this.power = power;
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
