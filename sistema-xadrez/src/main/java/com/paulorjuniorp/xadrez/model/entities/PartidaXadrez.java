package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.model.entities.Peca;
import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.pecas.Rei;
import com.paulorjuniorp.xadrez.model.entities.pecas.Torre;
import com.paulorjuniorp.xadrez.model.enums.Color;
import com.paulorjuniorp.xadrez.model.exceptions.XadrezException;

public class PartidaXadrez {
    private int turno;
    private Color jogadorAtual;
    private Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        this.turno = 1;
        this.jogadorAtual = Color.WHITE;
        configuracaoInicial();
    }

    public int getTurno(){
        return turno;
    }

    public Color getJogadorAtual(){
        return jogadorAtual;
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

    public boolean[][] movimentosPosssiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.toPosicao();
        validaPosicaoOrigem(posicao);

        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez executaJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();
        Posicao destino = posicaoDestino.toPosicao();
        validaPosicaoOrigem(origem);
        validaPosicaoDestino(origem, destino);
        Peca pecaCapturada = criaMovimento(origem, destino);
        proximoTurno();

        return (PecaXadrez) pecaCapturada;
    }

    private Peca criaMovimento(Posicao origem, Posicao destino) {
        Peca peca = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);

        tabuleiro.pecaNoLugar(peca, destino);

        return pecaCapturada;
    }

    private void validaPosicaoOrigem(Posicao origem) {
        if (!tabuleiro.existeUmaPeca(origem)){
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(origem)).getColor()){
            throw new XadrezException("A peça escolhida não é sua");
        }
        if (!tabuleiro.peca(origem).existeAlgumMovimentoPossivel()){
            throw new XadrezException("Não existe movimentos para a peça escolhida.");
        }
    }

    private void validaPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)){
            throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
        }
    }
    private void colocaNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
    }

    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
