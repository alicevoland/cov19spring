package com.mvoland.cov19api.tomove.data.update.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class UpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataSource;
    private LocalDateTime requestTime;
    private Boolean completed;

    public UpdateRequest() {
    }

    public UpdateRequest(String dataSource, LocalDateTime requestTime, Boolean completed) {
        this.dataSource = dataSource;
        this.requestTime = requestTime;
        this.completed = completed;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String data) {
        this.dataSource = data;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Duration getDurationSinceRequest() {
        return Duration.between(requestTime, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateRequest updateRequest = (UpdateRequest) o;
        return Objects.equals(dataSource, updateRequest.dataSource) && Objects.equals(requestTime, updateRequest.requestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataSource, requestTime);
    }

    @Override
    public String toString() {
        return "Update{" +
                "id=" + id +
                ", data='" + dataSource + '\'' +
                ", requestTime=" + requestTime +
                '}';
    }
}
