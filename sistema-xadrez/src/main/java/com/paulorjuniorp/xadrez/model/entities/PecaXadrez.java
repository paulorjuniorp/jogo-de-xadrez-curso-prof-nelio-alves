package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.model.entities.Peca;
import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.enums.Color;

public abstract class PecaXadrez extends Peca {

    private Color color;
    private int contagemMovimento;

    public PecaXadrez(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro);
        this.color = color;
    }

    protected boolean existePecaAdversaria(Posicao posicao){
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return (pecaXadrez != null && pecaXadrez.getColor() != color);
    }

    public Color getColor() {
        return color;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.fromPosicao(posicao);
    }
}
