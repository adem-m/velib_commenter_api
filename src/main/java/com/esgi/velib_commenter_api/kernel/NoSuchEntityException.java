package com.esgi.velib_commenter_api.kernel;

public class NoSuchEntityException extends UnsupportedOperationException {
    public NoSuchEntityException(Class<?> entity, String id) {
        super(String.format("No %s with attribute %s", entity.getSimpleName(), id));
    }
}

