package ir.altontelecom.iotlamp.webservice.response;

import com.google.gson.annotations.SerializedName;

public class SetResponse {
    @SerializedName("Status")
    private Status status;

    public SetResponse() {
    }

    public SetResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
