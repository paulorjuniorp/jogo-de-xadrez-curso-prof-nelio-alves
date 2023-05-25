package com.paulorjuniorp.tabuleiro;

public class Piece {
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Piece(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
