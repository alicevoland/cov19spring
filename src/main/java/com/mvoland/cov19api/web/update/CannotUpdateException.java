package com.mvoland.cov19api.web;

public class CannotUpdateException extends RuntimeException {
    public CannotUpdateException(String dataSource) {
        super("Cannot update " + dataSource);
    }
}
