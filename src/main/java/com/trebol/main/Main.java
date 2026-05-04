package com.trebol.main;

import com.trebol.view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        // Las interfaces gráficas en Java deben correr en su propio "hilo" por seguridad
        java.awt.EventQueue.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.mostrar();
        });
    }
}