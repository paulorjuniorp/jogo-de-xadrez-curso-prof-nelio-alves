package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PosicaoXadrez;
import com.paulorjuniorp.xadrez.model.enums.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoXadrez entradaPosicaoXadrez(Scanner scanner){
        try {
            String entrada = scanner.nextLine();
            char coluna = entrada.charAt(0);
            int linha = Integer.parseInt(entrada.substring(1));

            return new PosicaoXadrez(coluna,linha);
        } catch (RuntimeException e){
            throw new InputMismatchException("Erro ao ler a posição. Os valores válidos estão entre a1 e h8.");
        }
    }

    public static void imprimePartida(PartidaXadrez partidaXadrez, List<PecaXadrez> pecasCapturadas){
        imprimeTabuleiro(partidaXadrez.getPecas());
        System.out.println();
        imprimePecasCapturadas(pecasCapturadas);
        System.out.println();
        System.out.println("Turno: " + partidaXadrez.getTurno());
        if (!partidaXadrez.getCheckMate()){
            System.out.println("Esperando jogador: " + partidaXadrez.getJogadorAtual());

            if (partidaXadrez.getCheck()){
                System.out.println("CHECK!!");
            }
        } else {
            System.out.println("CHECK MATE!!!");
            System.out.println("Vencedor: " + partidaXadrez.getJogadorAtual());
        }

    }
    public static void imprimeTabuleiro(PecaXadrez[][] pecasXadrez){
        for (int i = 0; i < pecasXadrez.length; i++){
            System.out.print((8-i) + " ");
            for (int j = 0; j < pecasXadrez.length; j++){
                imprimePeca(pecasXadrez[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void imprimeTabuleiro(PecaXadrez[][] pecasXadrez, boolean[][] movimentosPossiveis) {
        for (int i = 0; i < pecasXadrez.length; i++){
            System.out.print((8-i) + " ");
            for (int j = 0; j < pecasXadrez.length; j++){
                imprimePeca(pecasXadrez[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimePeca(PecaXadrez pecaXadrez, boolean background){
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }

        if (pecaXadrez == null){
            System.out.print("-");
        } else {
            if (pecaXadrez.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + pecaXadrez + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + pecaXadrez + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    public static void imprimePecasCapturadas(List<PecaXadrez> pecasXadrez){
        List<PecaXadrez> brancas = pecasXadrez.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<PecaXadrez> pretas = pecasXadrez.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());

        System.out.print(ANSI_WHITE);
        System.out.print("Brancas: ");
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.println(ANSI_RESET);

        System.out.print(ANSI_BLACK);
        System.out.print("Pretas: ");
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.println(ANSI_RESET);
    }
}
