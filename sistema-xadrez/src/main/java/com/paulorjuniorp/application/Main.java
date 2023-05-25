package com.paulorjuniorp.application;

import com.paulorjuniorp.xadrez.model.entities.PartidaXadrez;

public class Main {
    public static void main(String[] args) {
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.imprimeTabuleiro(partidaXadrez.getPecas());
    }
}