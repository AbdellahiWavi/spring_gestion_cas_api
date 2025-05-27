package com.cas.sur.tout.urgents.exception;

public enum ErrorCodes {
    INCIDENT_NOT_FOUND(1000),
    INCIDENT_NOT_VALID(1001),
    // TODO complete the rest of Error Codes
    CLIENT_NOT_FOUND(2000),
    CLIENT_NOT_VALID(2001),
    PLACE_NOT_FOUND(3000),
    PLACE_NOT_VALID(3001),
    DEGREE_NOT_FOUND(4000),
    DEGREE_NOT_VALID(4001),
    ORGANISME_NOT_FOUND(5000),
    ORGANISME_NOT_VALID(5001),
    TYPE_CAS_NOT_FOUND(6000),
    TYPE_CAS_NOT_VALID(6001),
    USER_NOT_FOUND(7000),
    USER_NOT_VALID(7001),
    GESTIONNAIRE_NOT_FOUND(8000),
    GESTIONNAIRE_NOT_VALID(8001),
    ROLE_NOT_FOUND(9000),
    ROLE_NOT_VALID(9001),
    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
