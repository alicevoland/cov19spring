package com.mvoland.cov19api.dataupdate.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class UpdateRequest {
    @NonNull
    private Boolean accepted;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime acceptedStartTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime requestBeginDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rejectionCause;

    public static UpdateRequest accepted() {
        return UpdateRequest.accepted(null);
    }

    public static UpdateRequest accepted(LocalDateTime noticeDateBegin) {
        return new UpdateRequest(
                true,
                LocalDateTime.now(),
                noticeDateBegin,
                null
        );
    }

    public static UpdateRequest rejected(String reason) {
        return new UpdateRequest(
                true,
                null,
                null,
                reason
        );

    }
}
