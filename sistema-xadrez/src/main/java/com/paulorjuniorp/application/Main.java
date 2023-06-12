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

        while (!partidaXadrez.getCheckMate()){
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

                if (partidaXadrez.getPromovido() != null){
                    System.out.print("Digite a peça que será promovida (B/T/Q/C): ");
                    String tipo = scanner.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("T") && !tipo.equals("C") && !tipo.equals("Q")){
                        System.out.print("Valor inválido!!! Digite a peça que será promovida (B/T/Q/C): ");
                        tipo = scanner.nextLine().toUpperCase();
                    }
                    partidaXadrez.substituiPecaPromovida(tipo);
                }
            } catch (XadrezException | InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
        UI.clearScreen();
        UI.imprimePartida(partidaXadrez,capturadas);
    }
}