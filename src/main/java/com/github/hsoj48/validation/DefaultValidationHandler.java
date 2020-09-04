package com.github.hsoj48.validation;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DefaultValidationHandler implements ValidationHandler {

    private static final String DEFAULT_FIELD_ERROR_PREFIX = "Invalid ";
    private static final String DEFAULT_ERROR_DELIMITER = ", ";

    private final Set<String> errorMessages = new HashSet<>();

    @Override
    public void add(ValidationHandler handler) {
        if (handler != null) {
            errorMessages.addAll(handler.getErrorMessages());
        }
    }

    @Override
    public void add(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    @Override
    public void add(List<String> errorMessages) {
        if (errorMessages != null) {
            this.errorMessages.addAll(errorMessages);
        }
    }

    @Override
    public void addFieldError(String fieldName) {
        add(DEFAULT_FIELD_ERROR_PREFIX + fieldName);
    }

    @Override
    public int errorCount() {
        return errorMessages.size();
    }

    @Override
    public boolean hasErrors() {
        return !errorMessages.isEmpty();
    }

    @Override
    public List<String> getErrorMessages() {
        return new ArrayList<>(errorMessages);
    }

    @Override
    public String getErrorMessagesJoined() {
        return getErrorMessagesJoined(DEFAULT_ERROR_DELIMITER);
    }

    @Override
    public String getErrorMessagesJoined(String delimiter) {
        return errorMessages.stream().sorted().collect(Collectors.joining(delimiter));
    }

    @Override
    public void onSuccess(Consumer<ValidationHandler> successFunction) {
        if (!hasErrors()) {
            successFunction.accept(this);
        }
    }

    @Override
    public void onError(Consumer<ValidationHandler> errorFunction) {
        if (hasErrors()) {
            errorFunction.accept(this);
        }
    }

    // ---------- fluent builder methods after here ----------

    @Override
    public StringFieldValidator requireThat(String fieldName, String fieldValue) {
        return requireThat(Collections.singletonList(fieldName), fieldValue);
    }

    @Override
    public StringFieldValidator requireThat(List<String> fieldNames, String fieldValue) {
        return new StringFieldValidator(this, fieldNames, fieldValue);
    }

    @Override
    public DateFieldValidator requireThat(String fieldName, Date fieldValue) {
        return requireThat(Collections.singletonList(fieldName), fieldValue);
    }

    @Override
    public DateFieldValidator requireThat(List<String> fieldNames, Date fieldValue) {
        return new DateFieldValidator(this, fieldNames, fieldValue);
    }

    @Override
    public <T> CollectionFieldValidator<T> requireThat(String fieldName, Collection<T> fieldValue) {
        return requireThat(Collections.singletonList(fieldName), fieldValue);
    }

    @Override
    public <T> CollectionFieldValidator<T> requireThat(List<String> fieldNames, Collection<T> fieldValue) {
        return new CollectionFieldValidator<>(this, fieldNames, fieldValue);
    }

    @Override
    public <T> GenericFieldValidator<T> requireThat(String fieldName, T fieldValue) {
        return requireThat(Collections.singletonList(fieldName), fieldValue);
    }

    @Override
    public <T> GenericFieldValidator<T> requireThat(List<String> fieldNames, T fieldValue) {
        return new GenericFieldValidator<>(this, fieldNames, fieldValue);
    }

}
