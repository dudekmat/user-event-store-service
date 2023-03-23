package com.github.dudekmat.usereventstoreservice.config.kafka;

import static java.util.Objects.nonNull;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@Slf4j
class EventListenerErrorHandler implements KafkaListenerErrorHandler {

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException ex) {
    var cause = ex.getRootCause();
    if (cause instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
      log.warn("Validation failed for event: {}, reason: {}", message.getPayload(),
          getValidationErrors(methodArgumentNotValidException));
    } else {
      log.error("Error occurred: {}", nonNull(cause) ? cause.getMessage() : "Unknown error");
    }
    return null;
  }

  private List<String> getValidationErrors(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrors =
        nonNull(bindingResult) ? bindingResult.getFieldErrors() : List.of();

    return fieldErrors.stream().map(
        error -> error.getField() + " " + error.getDefaultMessage()
    ).toList();
  }
}
