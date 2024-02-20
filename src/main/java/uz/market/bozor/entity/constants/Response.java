package uz.market.bozor.entity.constants;

import lombok.Getter;

public class Response {


    public enum BaseResponseMessage {
        SUCCESS("succeeded"),
        FAILED("failed");
        public final String message;

        BaseResponseMessage(String message) {
            this.message = message;
        }

    }


    public enum RequestStatus {
        COMPLETED, FAILED;
    }

    public static class HttpResponse {
        public static final String CREATE_USER = "User Created.";
        public static final String CONFIG_ROLE = "Role Configuration Completed.";
        public static final String CONFIG_GROUP = "Group Configuration Completed.";

        public static final String SETUP_SERVICE = "Service setup completed.";
        public static final String SETUP_SERVICE_FAILED = "Service setup failed.";
        public static final String SETUP_COMPANY = "Company setup completed.";
    }

}
