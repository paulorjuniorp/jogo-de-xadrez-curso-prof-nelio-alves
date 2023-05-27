package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.pecas.Rei;
import com.paulorjuniorp.xadrez.model.entities.pecas.Torre;
import com.paulorjuniorp.xadrez.model.enums.Color;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        configuracaoInicial();
    }

    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] matrizPecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                matrizPecas[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }

        return matrizPecas;
    }

    private void colocaNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
    }

    private void configuracaoInicial(){
        colocaNovaPeca('c', 2, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('d', 2, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('e', 2, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('e', 1, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('d', 1, new Rei(tabuleiro, Color.WHITE));

        colocaNovaPeca('c', 7, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('d', 7, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('e', 7, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('e', 8, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('d', 8, new Rei(tabuleiro, Color.BLACK));
    }
}
