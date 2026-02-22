package com.example.back.backend.common.Enum;

public class AccountEnum {

    public enum HistoryType {

        INSERT("INSERT"),
        REGISTER("REGISTER"),
        UPDATE("UPDATE"),
        DELETE("DELETE");

        private final String value;

        HistoryType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AccountStatus {

        ACTIVE("00"),
        NOT_ACTIVE("99");

        private final String value;

        AccountStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum HistoryDesc {

        MEMBER("MEMBER HISTORY INSERT"),
        MEMBER_INFO("MEMBER_INFO HISTORY INSERT"),
        MEMBER_CREDENTIAL("MEMBER_CREDENTIAL HISTORY INSERT"),
        PAYROLL("PAYROLL HISTORY INSERT");

        private final String value;

        HistoryDesc(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AuthProcType {

        CHECK_EMAIL("VERIFY_EMAIL"),
        CHANGE_PASS("CHANGE_PASS");

        private final String value;

        AuthProcType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AddPurpose {

        REGISTER("01"),
        ADD_USER("02");

        private final String value;

        AddPurpose(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum workspace {

        OWNER("OWNER"),
        CHILD("CHILD");

        private final String value;

        workspace(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

}
