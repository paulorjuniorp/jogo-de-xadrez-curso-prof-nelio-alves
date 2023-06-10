package com.paulorjuniorp.xadrez.model.entities;

import com.paulorjuniorp.tabuleiro.model.entities.Peca;
import com.paulorjuniorp.tabuleiro.model.entities.Posicao;
import com.paulorjuniorp.tabuleiro.model.entities.Tabuleiro;
import com.paulorjuniorp.xadrez.model.entities.pecas.*;
import com.paulorjuniorp.xadrez.model.enums.Color;
import com.paulorjuniorp.xadrez.model.exceptions.XadrezException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {
    private int turno;
    private Color jogadorAtual;
    private Tabuleiro tabuleiro;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();
    private boolean check;
    private boolean checkMate;
    private PecaXadrez vulneravelAoEnPassant;
    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8,8);
        this.turno = 1;
        this.jogadorAtual = Color.WHITE;
        configuracaoInicial();
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public int getTurno(){
        return turno;
    }

    public Color getJogadorAtual(){
        return jogadorAtual;
    }

    public PecaXadrez getVulneravelAoEnPassant() {
        return vulneravelAoEnPassant;
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

        if (testeCheck(jogadorAtual)){
            desfazMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode colocar você mesmo em check");
        }

        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

        check = testeCheck(oponente(jogadorAtual));

        if (testeCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        } else {
            proximoTurno();
        }

        // #movimento especial en passant
        if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() + 2 || destino.getLinha() == origem.getLinha() - 2)){
            vulneravelAoEnPassant = pecaMovida;
        } else {
            vulneravelAoEnPassant = null;
        }

        return (PecaXadrez) pecaCapturada;
    }

    private Peca criaMovimento(Posicao origem, Posicao destino) {
        PecaXadrez peca =(PecaXadrez) tabuleiro.removePeca(origem);
        peca.incrementaContagemMovimento();
        Peca pecaCapturada = tabuleiro.removePeca(destino);

        tabuleiro.pecaNoLugar(peca, destino);

        if (pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        // roque pequeno
        if (peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
            tabuleiro.pecaNoLugar(torre,destinoTorre);
            torre.incrementaContagemMovimento();
        }

        // roque grande
        if (peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemTorre);
            tabuleiro.pecaNoLugar(torre,destinoTorre);
            torre.incrementaContagemMovimento();
        }

        // en passant
        if (peca instanceof Peao){
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null){
                Posicao posicaoPeao;
                if (peca.getColor() == Color.WHITE){
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }

                pecaCapturada = tabuleiro.removePeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);
            }
        }

        return pecaCapturada;
    }

    private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada){
        PecaXadrez peca = (PecaXadrez)tabuleiro.removePeca(destino);
        peca.decrementaContagemMovimento();

        tabuleiro.pecaNoLugar(peca, origem);

        if (pecaCapturada != null){
            tabuleiro.pecaNoLugar(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }

        // roque pequeno
        if (peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
            tabuleiro.pecaNoLugar(torre,origemTorre);
            torre.decrementaContagemMovimento();
        }

        // roque grande
        if (peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoTorre);
            tabuleiro.pecaNoLugar(torre,origemTorre);
            torre.decrementaContagemMovimento();
        }

        // en passant
        if (peca instanceof Peao){
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == vulneravelAoEnPassant){
                PecaXadrez peao = (PecaXadrez) tabuleiro.removePeca(destino);
                Posicao posicaoPeao;
                if (peca.getColor() == Color.WHITE){
                    posicaoPeao = new Posicao(3, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.pecaNoLugar(peca, posicaoPeao);
            }
        }
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

    private Color oponente(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private PecaXadrez rei(Color color){
        List<Peca> listaPecas = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
        for (Peca peca : listaPecas){
            if (peca instanceof Rei){
                return (PecaXadrez) peca;
            }
        }
        throw new IllegalStateException("Não existe o rei da cor " + color + " no tabuleiro");
    }

    private boolean testeCheck(Color color){
        Posicao posicaoRei = rei(color).getPosicaoXadrez().toPosicao();
        List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());

        for (Peca peca : pecasOponentes){
            boolean[][] matriz = peca.movimentosPossiveis();
            if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]){
                return true;
            }
        }

        return false;
    }

    private boolean testeCheckMate(Color color){
        if (!testeCheck(color)){
            return false;
        }

        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
        for (Peca peca : lista){
            boolean[][] matriz = peca.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++){
                for (int j = 0; j < tabuleiro.getColunas(); j++){
                    if (matriz[i][j]){
                        Posicao origem = ((PecaXadrez) peca).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i,j);
                        Peca pecaCapturada = criaMovimento(origem, destino);
                        boolean testeCheck = testeCheck(color);
                        desfazMovimento(origem,destino,pecaCapturada);
                        if (!testeCheck){
                            return false;
                        }

                    }
                }
            }
        }

        return true;
    }
    private void colocaNovaPeca(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.pecaNoLugar(pecaXadrez, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasNoTabuleiro.add(pecaXadrez);
    }

    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void configuracaoInicial(){
        colocaNovaPeca('a', 1, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('b',1, new Cavalo(tabuleiro, Color.WHITE));
        colocaNovaPeca('c',1, new Bispo(tabuleiro, Color.WHITE));
        colocaNovaPeca('d',1, new Rainha(tabuleiro, Color.WHITE));
        colocaNovaPeca('e', 1, new Rei(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('f',1, new Bispo(tabuleiro, Color.WHITE));
        colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Color.WHITE));
        colocaNovaPeca('h', 1, new Torre(tabuleiro, Color.WHITE));
        colocaNovaPeca('a',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('b',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('c',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('d',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('e',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('f',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('g',2, new Peao(tabuleiro, Color.WHITE, this));
        colocaNovaPeca('h',2, new Peao(tabuleiro, Color.WHITE, this));


        colocaNovaPeca('a', 8, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('b',8, new Cavalo(tabuleiro, Color.BLACK));
        colocaNovaPeca('c', 8, new Bispo(tabuleiro, Color.BLACK));
        colocaNovaPeca('d',8, new Rainha(tabuleiro, Color.BLACK));
        colocaNovaPeca('e', 8, new Rei(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('f', 8, new Bispo(tabuleiro, Color.BLACK));
        colocaNovaPeca('g',8, new Cavalo(tabuleiro, Color.BLACK));
        colocaNovaPeca('h', 8, new Torre(tabuleiro, Color.BLACK));
        colocaNovaPeca('a',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('b',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('c',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('d',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('e',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('f',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('g',7, new Peao(tabuleiro, Color.BLACK, this));
        colocaNovaPeca('h',7, new Peao(tabuleiro, Color.BLACK, this));
    }
}
