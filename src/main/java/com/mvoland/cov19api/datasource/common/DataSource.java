package com.mvoland.cov19api.datasource.common;

import java.time.LocalDate;

public interface DataSource {

    String getSourceName();

    void fullUpdate();

    void updateSince(LocalDate date);
}
