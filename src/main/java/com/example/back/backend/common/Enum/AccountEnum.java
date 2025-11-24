package com.example.back.backend.common.Enum;

public class AccountEnum {

    public enum HistoryType {

        MEMBER("1"),
        MEMBER_INFO("2"),
        MEMBER_CREDENTIAL("3"),
        PAYROLL("4");

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

}
