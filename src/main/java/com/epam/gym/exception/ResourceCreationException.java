package com.epam.gym.exception;

public class ResourceCreationException extends RuntimeException {

    public ResourceCreationException(Class<?> clazz) {
        super(String.format("Error while creating %s", clazz.getSimpleName()));
    }
}
