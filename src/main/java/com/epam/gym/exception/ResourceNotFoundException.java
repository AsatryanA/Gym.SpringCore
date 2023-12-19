package com.epam.gym.exception;

import javax.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException(Class<?> clazz, Long id) {
        super(String.format("%s with id %s not found", clazz.getSimpleName(), id));
    }

    public ResourceNotFoundException(Class<?> clazz, String username) {
        super(String.format("%s with username %s not found", clazz.getSimpleName(), username));
    }
}
