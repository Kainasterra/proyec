package com.trebol.utils;

/**
 * Clase auxiliar para manejar pares ID-Valor dentro de componentes JComboBox.
 * El método toString() define qué es lo que el usuario verá en la lista.
 */
public class ComboItem {
    private int id;
    private String etiqueta;

    public ComboItem(int id, String etiqueta) {
        this.id = id;
        this.etiqueta = etiqueta;
    }

    public int getId() {
        return id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        // Esto es lo que se mostrará en el ComboBox
        return etiqueta;
    }
}