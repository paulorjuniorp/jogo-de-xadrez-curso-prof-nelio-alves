package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;

public class UI {
    public static void imprimeTabuleiro(PecaXadrez[][] pecasXadrez){
        for (int i = 0; i < pecasXadrez.length; i++){
            System.out.print((8-i) + " ");
            for (int j = 0; j < pecasXadrez.length; j++){
                imprimePeca(pecasXadrez[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void imprimePeca(PecaXadrez pecaXadrez){
        if (pecaXadrez == null){
            System.out.print("-");
        } else {
            System.out.print(pecaXadrez);
        }
        System.out.print(" ");
    }
}
