package com.paulorjuniorp.xadrez.model.exceptions;

import com.paulorjuniorp.tabuleiro.model.exception.TabuleiroException;

public class XadrezException extends TabuleiroException {
    public XadrezException(String message) {
        super(message);
    }
}
