package com.h2rd.refactoring.util;

/**
 * Created by adil on 08/10/18.
 *
 * This Enum could be enriched to include
 * other unspecified status codes in
 * the Response object
 */
public enum StatusCode {

    OK_STATUS(200, "Ok"),
    CREATED_STATUS(201, "Created"),
    NO_CONTENT_STATUS(204, "No Content"),
    BAD_REQUEST_STATUS(400, "Bad Request"),
    NOT_FOUND_STATUS(404, "Not Found");

    private int code;
    private String codeDescription;

    StatusCode(int code, String codeDescription) {
        this.code = code;
        this.codeDescription = codeDescription;
    }

    public int getCode() {
        return code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }
}
