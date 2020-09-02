package com.artefact.validation;

public interface Validator<T> {

    void validate(T request);

}
