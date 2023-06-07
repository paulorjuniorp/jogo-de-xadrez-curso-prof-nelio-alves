package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Rainha extends PecaXadrez {
    public Rainha(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        return new boolean[0][];
    }

    @Override
    public String toString() {
        return "Q";
    }
}
