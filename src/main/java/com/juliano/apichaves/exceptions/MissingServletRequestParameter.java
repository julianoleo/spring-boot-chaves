package com.juliano.apichaves.exceptions;

public class MissingServletRequestParameter extends RuntimeException{
    public MissingServletRequestParameter(String message) { super(message);}
}
