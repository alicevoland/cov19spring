package com.mvoland.cov19api.tomove.web.update;

public class CannotUpdateException extends RuntimeException {
    public CannotUpdateException(String dataSource) {
        super("Cannot update " + dataSource);
    }
}
