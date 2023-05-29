package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PecaXadrez;
import com.paulorjuniorp.xadrez.model.entities.PosicaoXadrez;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        Scanner scanner = new Scanner(System.in);
        while (true){
            UI.imprimeTabuleiro(partidaXadrez.getPecas());
            System.out.println();
            System.out.print("Origem: ");
            PosicaoXadrez origem = UI.entradaPosicaoXadrez(scanner);

            System.out.print("Destino: ");
            PosicaoXadrez destino = UI.entradaPosicaoXadrez(scanner);

            PecaXadrez pecaCapturada = partidaXadrez.executaJogadaXadrez(origem,destino);
        }
    }
}