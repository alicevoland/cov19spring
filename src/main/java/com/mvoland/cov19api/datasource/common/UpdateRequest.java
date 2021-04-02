package com.mvoland.cov19api.datasource.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class UpdateRequest {
    @NonNull
    private Boolean accepted;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String info;

    public static UpdateRequest accepted() {
        return new UpdateRequest(true);
    }

    public static UpdateRequest rejected(String reason) {
        return new UpdateRequest(false, reason);
    }
}
