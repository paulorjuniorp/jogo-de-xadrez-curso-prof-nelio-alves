package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PosicaoXadrez;
import com.paulorjuniorp.xadrez.model.exceptions.XadrezException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        Scanner scanner = new Scanner(System.in);
        List<PecaXadrez> capturadas = new ArrayList<>();

        while (true){
            try {
                UI.clearScreen();
                UI.imprimePartida(partidaXadrez, capturadas);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = UI.entradaPosicaoXadrez(scanner);

                boolean[][] movimentosPossiveis = partidaXadrez.movimentosPosssiveis(origem);
                UI.clearScreen();
                UI.imprimeTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);

                System.out.print("Destino: ");
                PosicaoXadrez destino = UI.entradaPosicaoXadrez(scanner);

                PecaXadrez pecaCapturada = partidaXadrez.executaJogadaXadrez(origem,destino);
                if (pecaCapturada != null){
                    capturadas.add(pecaCapturada);
                }
            } catch (XadrezException | InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }
}