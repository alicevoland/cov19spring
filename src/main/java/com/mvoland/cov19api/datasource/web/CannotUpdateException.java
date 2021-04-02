package com.mvoland.cov19api.datasource.web;

class CannotUpdateException extends RuntimeException {

    public CannotUpdateException(String reason) {
        super("Could not update " + reason);
    }
}