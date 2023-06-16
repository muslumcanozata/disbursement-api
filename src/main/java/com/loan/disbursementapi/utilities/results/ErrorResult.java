package com.loan.disbursementapi.utilities.results;

public class ErrorResult {
    private final boolean success;
    private final String message;
    public ErrorResult(String message) {
        this.success = false;
        this.message = message;
    }
}
