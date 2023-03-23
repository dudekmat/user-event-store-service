package com.github.dudekmat.usereventstoreservice.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSearchEvent(@NotBlank String eventType, @NotBlank String userId,
                              @NotBlank String sessionId, @NotNull Long eventTime,
                              @NotBlank String productId, @NotBlank String query,
                              String searchParams, String platform) implements UserEvent {


}
