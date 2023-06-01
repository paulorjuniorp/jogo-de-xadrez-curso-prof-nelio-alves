package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PosicaoXadrez;
import com.paulorjuniorp.xadrez.model.exceptions.XadrezException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                UI.clearScreen();
                UI.imprimePartida(partidaXadrez);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.entradaPosicaoXadrez(scanner);

                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPosssiveis(origem);
                UI.clearScreen();
                UI.imprimeTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);

                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.entradaPosicaoXadrez(scanner);

                PecaXadrez pecaCapturada = partidaXadrez.executaJogadaXadrez(origem,destino);
            } catch (XadrezException | InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }
}