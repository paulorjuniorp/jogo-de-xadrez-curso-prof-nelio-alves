package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Bispo extends PecaXadrez {
    public Bispo(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matrizAux = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0,0);

        // Noroeste
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setValores(posicaoAux.getLinha() -1, posicaoAux.getColuna() -1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Nordeste
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setValores(posicaoAux.getLinha() -1, posicaoAux.getColuna() + 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudeste
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setValores(posicaoAux.getLinha() + 1, posicaoAux.getColuna() + 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudoeste
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setValores(posicaoAux.getLinha() + 1, posicaoAux.getColuna() - 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        return matrizAux;
    }

    @Override
    public String toString() {
        return "B";
    }
}
