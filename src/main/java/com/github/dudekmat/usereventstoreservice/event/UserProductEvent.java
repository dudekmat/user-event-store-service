package com.github.dudekmat.usereventstoreservice.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserProductEvent(@NotBlank String eventType, @NotBlank String userId,
                               @NotBlank String sessionId, @NotNull Long eventTime,
                               @NotBlank String productId, String pageName, String boxName,
                               String boxPosition, String platform) implements UserEvent {

}
