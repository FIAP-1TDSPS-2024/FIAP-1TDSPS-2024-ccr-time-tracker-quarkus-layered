package br.com.fiap.exception;

public class ErrorResponse {
    public String error;
    public int status;
    public String message;

    public ErrorResponse(String error, int status, String message) {
        this.error = error;
        this.status = status;
        this.message = message;
    }
}
