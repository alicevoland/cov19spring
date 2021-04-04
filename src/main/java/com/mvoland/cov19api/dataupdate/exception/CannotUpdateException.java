package com.mvoland.cov19api.dataupdate.exception;

public class CannotUpdateException extends RuntimeException {

    public CannotUpdateException(String reason) {
        super("Could not update " + reason);
    }
}