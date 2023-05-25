package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.Peca;
import com.paulorjuniorp.tabuleiro.Tabuleiro;
import com.paulorjuniorp.xadrez.enums.Color;

public class PecaXadrez extends Peca {

    private Color color;
    private int contagemMovimento;

    public PecaXadrez(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
