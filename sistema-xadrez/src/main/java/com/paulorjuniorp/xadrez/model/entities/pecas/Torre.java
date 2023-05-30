package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matrizAux = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0,0);

        // Acima
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setLinha(posicaoAux.getLinha() - 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Esquerda
        posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setColuna(posicaoAux.getColuna() - 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Direita
        posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setColuna(posicaoAux.getColuna() + 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Baixo
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existePecaPosicao(posicaoAux) && !getTabuleiro().existeUmaPeca(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setLinha(posicaoAux.getLinha() + 1);
        }
        if (getTabuleiro().existePecaPosicao(posicaoAux) && existePecaAdversaria(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        return matrizAux;
    }
}
