package com.esgi.velib_commenter_api.modules.authentification.application;

final public class NoTokenProvidedException extends RuntimeException {
    public NoTokenProvidedException() {
        super("No token provided");
    }
}
