package com.github.hsoj48.validation;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public interface ValidationHandler {

    void add(ValidationHandler handler);
    void add(String errorMessage);
    void add(List<String> errorMessages);
    void addFieldError(String fieldName);

    int errorCount();
    boolean hasErrors();
    List<String> getErrorMessages();
    String getErrorMessagesJoined();
    String getErrorMessagesJoined(String delimiter);

    void onSuccess(Consumer<ValidationHandler> successFunction);
    void onError(Consumer<ValidationHandler> errorFunction);

    StringFieldValidator requireThat(String fieldName, String fieldValue);
    StringFieldValidator requireThat(List<String> fieldNames, String fieldValue);
    DateFieldValidator requireThat(String fieldName, Date fieldValue);
    DateFieldValidator requireThat(List<String> fieldNames, Date fieldValue);
    InstantFieldValidator requireThat(String fieldName, Instant fieldValue);
    InstantFieldValidator requireThat(List<String> fieldNames, Instant fieldValue);
    <T> CollectionFieldValidator<T> requireThat(String fieldName, Collection<T> fieldValue);
    <T> CollectionFieldValidator<T> requireThat(List<String> fieldNames, Collection<T> fieldValue);
    <T> GenericFieldValidator<T> requireThat(String fieldName, T fieldValue);
    <T> GenericFieldValidator<T> requireThat(List<String> fieldNames, T fieldValue);

}
