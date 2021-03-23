package com.mvoland.cov19api.update.web;

public class CannotUpdateException extends RuntimeException {
    public CannotUpdateException(String dataSource) {
        super("Cannot update " + dataSource);
    }
}
