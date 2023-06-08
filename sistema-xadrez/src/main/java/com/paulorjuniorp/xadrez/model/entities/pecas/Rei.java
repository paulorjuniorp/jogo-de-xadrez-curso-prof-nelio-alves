package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PosicaoXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Rei extends PecaXadrez {

    private PartidaXadrez partidaXadrez;
    public Rei(Tabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
        super(tabuleiro, color);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez == null || pecaXadrez.getColor() != getColor();
    }

    private boolean testeRoqueTorre(Posicao posicao){
        PecaXadrez pecaXadrez = (PecaXadrez) getTabuleiro().peca(posicao);
        return pecaXadrez != null && pecaXadrez instanceof Torre && pecaXadrez.getColor() == getColor() && pecaXadrez.getContagemMovimento() == 0;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matrizAux = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0,0);

        // Acima
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Abaixo
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Esquerda
        posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Direita
        posicaoAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Noroeeste
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Nordeste
        posicaoAux.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudoete
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudeste
        posicaoAux.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existePecaPosicao(posicaoAux) && podeMover(posicaoAux)){
            matrizAux[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // #movimento especial de roque
        if (getContagemMovimento() == 0 && !partidaXadrez.getCheck()){
            // #movimento especial da torre do lado do rei
            Posicao posicaoT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeRoqueTorre(posicaoT1)){
                Posicao posicaoUmDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao posicaoDoisDireita = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(posicaoUmDireita) == null && getTabuleiro().peca(posicaoDoisDireita) == null){
                    matrizAux[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }

            // #movimento especial da torre do lado da rainha
            Posicao posicaoT2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
            if (testeRoqueTorre(posicaoT2)){
                Posicao posicaoUmEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao posicaoDoisEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao posicaoTresEsquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(posicaoUmEsquerda) == null && getTabuleiro().peca(posicaoDoisEsquerda) == null && getTabuleiro().peca(posicaoTresEsquerda) == null){
                    matrizAux[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return matrizAux;
    }
}
