package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Peao extends PecaXadrez {
    public Peao(Tabuleiro tabuleiro, Color color) {
        super(tabuleiro, color);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao posicaoPeca = new Posicao(0,0);

        if (getColor() == Color.WHITE){
            posicaoPeca.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && !getTabuleiro().existeUmaPeca(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao posicaoPeca2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && !getTabuleiro().existeUmaPeca(posicaoPeca)
                    && getTabuleiro().existePecaPosicao(posicaoPeca2)
                    && !getTabuleiro().existeUmaPeca(posicaoPeca2)
                    && getContagemMovimento() == 0){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && existePecaAdversaria(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && existePecaAdversaria(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }
        } else {
            posicaoPeca.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && !getTabuleiro().existeUmaPeca(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao posicaoPeca2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && !getTabuleiro().existeUmaPeca(posicaoPeca)
                    && getTabuleiro().existePecaPosicao(posicaoPeca2)
                    && !getTabuleiro().existeUmaPeca(posicaoPeca2)
                    && getContagemMovimento() == 0){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && existePecaAdversaria(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }

            posicaoPeca.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePecaPosicao(posicaoPeca) && existePecaAdversaria(posicaoPeca)){
                matriz[posicaoPeca.getLinha()][posicaoPeca.getColuna()] = true;
            }
        }


        return new boolean[0][];
    }
}
