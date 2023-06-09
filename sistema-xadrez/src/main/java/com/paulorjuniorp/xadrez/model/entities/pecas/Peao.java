package com.paulorjuniorp.xadrez.model.entities.pecas;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;
    public Peao(Tabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
        super(tabuleiro, color);
        this.partidaXadrez = partidaXadrez;
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

            // #movimento especial en passant brancas
            if (posicao.getLinha() == 3){
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePecaPosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVulneravelAoEnPassant()){
                    matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }

                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePecaPosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVulneravelAoEnPassant()){
                    matriz[direita.getLinha() - 1][direita.getColuna()] = true;
                }
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

            // #movimento especial en passant pretas
            if (posicao.getLinha() == 4){
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existePecaPosicao(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getVulneravelAoEnPassant()){
                    matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }

                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existePecaPosicao(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaXadrez.getVulneravelAoEnPassant()){
                    matriz[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }


        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }
}
