package com.sadatmalik.factories;

/**
 * Since the factory may fail in its creation of a requested Shape, an
 * appropriate exception is needed
 *
 * @author sm@creativefusion.net
 */
public class BadShapeCreationException extends Exception {
    public BadShapeCreationException(String msg) {
        super(msg);
    }
}