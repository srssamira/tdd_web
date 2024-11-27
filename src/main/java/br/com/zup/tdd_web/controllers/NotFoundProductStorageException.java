package br.com.zup.tdd_web.controllers;

public class NotFoundProductStorageException extends RuntimeException {

    public NotFoundProductStorageException(String message) {
        super(message);
    }
}
