package com.github.hsoj48.validation;

public interface ValidationHandlerFactory<T extends ValidationHandler> {

    T newInstance();

}
