package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter; // Nueva importación
import java.awt.*;

public class PanelClientes extends JPanel {
    private JTextField txtNombre, txtTelefono, txtCorreo, txtDireccion;
    private JTextField txtBuscar; // Nuevo campo
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter; // El encargado del filtro

    public PanelClientes() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));

        // --- PANEL DE FORMULARIO (NORTE) ---
        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        panelNorte.setBackground(new Color(245, 245, 245));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtDireccion = new JTextField(20);

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
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelForm.add(panelBotones, gbc);
        
        panelNorte.add(panelForm, BorderLayout.CENTER);

        // --- BARRA DE BÚSQUEDA (NUEVO) ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(new Color(245, 245, 245));
        txtBuscar = new JTextField(30);
        panelBusqueda.add(new JLabel("🔍 Buscar cliente:"));
        panelBusqueda.add(txtBuscar);
        
        panelNorte.add(panelBusqueda, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);

        // --- TABLA ---
        modeloTabla = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Teléfono", "Correo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tablaClientes = new JTable(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla); // Inicializamos el sorter
        tablaClientes.setRowSorter(sorter); // Lo vinculamos a la tabla
        
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
    }

    private void colocarComponente(JPanel p, JComponent c, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x; gbc.gridy = y; gbc.gridwidth = 1;
        p.add(c, gbc);
    }

    // Getters
    public JTextField getTxtBuscar() { return txtBuscar; }
    public TableRowSorter<DefaultTableModel> getSorter() { return sorter; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JTable getTablaClientes() { return tablaClientes; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}