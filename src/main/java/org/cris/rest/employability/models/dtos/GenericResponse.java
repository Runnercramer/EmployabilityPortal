package org.cris.rest.employability.models.dtos;


public class GenericResponse {
    private String message;
    private int status;
    private String reason;

    public GenericResponse(){}
    public GenericResponse(String message, int code, String reason) {
        this.message = message;
        this.status = code;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
