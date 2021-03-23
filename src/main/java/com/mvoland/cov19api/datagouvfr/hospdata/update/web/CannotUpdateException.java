package com.mvoland.cov19api.datagouvfr.hospdata.update.web;

public class CannotUpdateException extends RuntimeException {
    public CannotUpdateException(String dataSource) {
        super("Cannot update " + dataSource);
    }
}
