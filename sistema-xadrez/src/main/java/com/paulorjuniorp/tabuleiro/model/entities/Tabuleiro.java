package com.paulorjuniorp.tabuleiro.model.entities;

import com.paulorjuniorp.tabuleiro.model.exception.TabuleiroException;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1){
            throw new TabuleiroException("Error na criação do tabuleiro: Deve haver ao menos 1 linha e 1 coluna");
        }

        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public Peca peca(int linha, int coluna){
        if (!existePecaPosicao(linha,coluna)){
            throw new TabuleiroException("Posição fora do tabuleiro");
        }

        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao){
        if (!existePecaPosicao(posicao)){
            throw new TabuleiroException("Posição fora do tabuleiro");
        }

        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void pecaNoLugar(Peca peca, Posicao posicao){
        if (existePecaPosicao(posicao)){
            throw new TabuleiroException("Já existe uma peça na posição " + posicao);
        }

        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    public boolean existeUmaPeca(Posicao posicao){
        if (!existePecaPosicao(posicao)){
            throw new TabuleiroException("Posição fora do tabuleiro");
        }

        return  peca(posicao) != null;
    }

    public boolean existePecaPosicao(Posicao posicao){
        return existePecaPosicao(posicao.getLinha(), posicao.getColuna());
    }

    private boolean existePecaPosicao(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }
}
