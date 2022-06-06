package com.esgi.velib_commenter_api.kernel;

public class NoSuchEntityException extends UnsupportedOperationException {
    public NoSuchEntityException(Class<?> entity) {
        super(String.format("No such %s", entity.getSimpleName()));
    }
}

