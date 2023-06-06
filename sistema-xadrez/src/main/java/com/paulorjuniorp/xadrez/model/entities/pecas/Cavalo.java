package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Cavalo extends PecaXadrez {
    public Cavalo(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez == null || pecaXadrez.getColor() != getColor();
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matrizAux = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0,0);

        // Acima
        posicaoAux.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.setValores(posicao.getLinha() - 2, posicao.getColuna() -1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Abaixo
        posicaoAux.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Esquerda
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Direita
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        return matrizAux;
    }

    @Override
    public String toString() {
        return "C";
    }
}
