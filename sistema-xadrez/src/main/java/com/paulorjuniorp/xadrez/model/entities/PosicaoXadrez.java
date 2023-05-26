package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    protected Posicao toPosicao(){
        return null;
    }

    protected PosicaoXadrez fromPosicao(Posicao posicao){
        return null;
    }
}
