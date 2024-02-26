package com.example.demo.util;

import java.text.MessageFormat;

public interface  DemoConstant {

    public interface USERS_STATUS {
        public static final String ACTIVE = "ACTIVE";
        public static final String INACTIVE = "INACTIVE";
    }


    public static interface RESPONSE_STATUS_CODE {
        public static final String FIND_NOT_FOUND_USER = "U404";
        public static final String USER_ALREADY = "U403";
        public static final String INVALID_PARAM = "P999";
        public static final String SUCCESS = "0000";
        public static final String ERROR = "E999";

    }

    public static enum RESPONSE_MESSAGE {

        SUCCESS(RESPONSE_STATUS_CODE.SUCCESS, "Success"),

        ERROR (RESPONSE_STATUS_CODE.ERROR, "Error {0}"),
        INVALID_PARAM (RESPONSE_STATUS_CODE.INVALID_PARAM, "Fail Request parameter(s) are invalid {0}"),
        FIND_NOT_FOUND_USER (RESPONSE_STATUS_CODE.FIND_NOT_FOUND_USER, "User not found"),
        //MISSING_OR_INVALID_PARAM (RESPONSE_STATUS_CODE.USER_ALREADY, "Missing or invalid parameter: {0}"),
        USER_ALREADY (RESPONSE_STATUS_CODE.USER_ALREADY, "User already exists");

        private String code;
        private String desc;

        RESPONSE_MESSAGE(String code, String desc){
            this.code = code;
            this.desc = desc;
        }
        public String getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
        public String getDesc(Object... args){
            return MessageFormat.format(desc, args);
        }



    }




}
