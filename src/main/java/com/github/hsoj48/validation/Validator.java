package com.github.hsoj48.validation;

public interface Validator<T> {

    void validate(T request);

}
