package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelClientes extends JPanel {
    private JTextField txtNombre, txtTelefono, txtCorreo;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public PanelClientes() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));

        // --- PANEL DE FORMULARIO (NORTE) ---
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtCorreo = new JTextField(20);
      

        // Etiquetas y Campos
        colocarComponente(panelForm, new JLabel("Nombre:"), 0, 0, gbc);
        colocarComponente(panelForm, txtNombre, 1, 0, gbc);
        colocarComponente(panelForm, new JLabel("Teléfono:"), 0, 1, gbc);
        colocarComponente(panelForm, txtTelefono, 1, 1, gbc);
        colocarComponente(panelForm, new JLabel("Correo:"), 0, 2, gbc);
        colocarComponente(panelForm, txtCorreo, 1, 2, gbc);
   

        // --- PANEL DE BOTONES ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(46, 204, 113)); // Verde
        btnGuardar.setForeground(Color.WHITE);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(52, 152, 219)); // Azul
        btnActualizar.setForeground(Color.WHITE);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(231, 76, 60)); // Rojo
        btnEliminar.setForeground(Color.WHITE);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelForm.add(panelBotones, gbc);

        add(panelForm, BorderLayout.NORTH);

        // --- PANEL DE TABLA (CENTRO) ---
        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Teléfono", "Correo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
    }

    private void colocarComponente(JPanel p, JComponent c, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        p.add(c, gbc);
    }

    // Getters para el controlador
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtCorreo() { return txtCorreo; }
   
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JTable getTablaClientes() { return tablaClientes; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}