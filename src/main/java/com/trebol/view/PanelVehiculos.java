package com.trebol.view;

import com.trebol.utils.ComboItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter; // Nueva importación
import java.awt.*;

public class PanelVehiculos extends JPanel {
    private JComboBox<ComboItem> cbClientes; 
    private JButton btnRefrescarCombo;
    private JTextField txtPlacas, txtMarca, txtModelo, txtAnio, txtBuscar; // txtBuscar añadido
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter; // El encargado del filtro

    public PanelVehiculos() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));

        // --- PANEL NORTE (Formulario + Búsqueda) ---
        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        panelNorte.setBackground(new Color(245, 245, 245));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Vehículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cbClientes = new JComboBox<>();
        btnRefrescarCombo = new JButton("🔄");
        JPanel panelCombo = new JPanel(new BorderLayout(5, 0));
        panelCombo.setBackground(Color.WHITE);
        panelCombo.add(cbClientes, BorderLayout.CENTER);
        panelCombo.add(btnRefrescarCombo, BorderLayout.EAST);

        txtPlacas = new JTextField(15);
        txtMarca = new JTextField(15);
        txtModelo = new JTextField(15);
        txtAnio = new JTextField(15);

        colocarComponente(panelForm, new JLabel("Cliente:"), 0, 0, gbc);
        colocarComponente(panelForm, panelCombo, 1, 0, gbc);
        colocarComponente(panelForm, new JLabel("Placas:"), 0, 1, gbc);
        colocarComponente(panelForm, txtPlacas, 1, 1, gbc);
        colocarComponente(panelForm, new JLabel("Marca:"), 0, 2, gbc);
        colocarComponente(panelForm, txtMarca, 1, 2, gbc);
        colocarComponente(panelForm, new JLabel("Modelo:"), 0, 3, gbc);
        colocarComponente(panelForm, txtModelo, 1, 3, gbc);
        colocarComponente(panelForm, new JLabel("Año:"), 0, 4, gbc);
        colocarComponente(panelForm, txtAnio, 1, 4, gbc);

        // Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.WHITE);
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panelForm.add(panelBotones, gbc);
        panelNorte.add(panelForm, BorderLayout.CENTER);

        // --- BARRA DE BÚSQUEDA ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBackground(new Color(245, 245, 245));
        txtBuscar = new JTextField(30);
        panelBusqueda.add(new JLabel("🔍 Buscar vehículo:"));
        panelBusqueda.add(txtBuscar);
        panelNorte.add(panelBusqueda, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);

        // --- TABLA ---
        modeloTabla = new DefaultTableModel(
            new String[]{"ID Vehículo", "ID Cliente", "Placas", "Marca", "Modelo", "Año"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaVehiculos = new JTable(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        tablaVehiculos.setRowSorter(sorter);
        
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
    }

    private void colocarComponente(JPanel p, JComponent c, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x; gbc.gridy = y; gbc.gridwidth = 1;
        p.add(c, gbc);
    }

    // Getters
    public JTextField getTxtBuscar() { return txtBuscar; }
    public TableRowSorter<DefaultTableModel> getSorter() { return sorter; }
    public JComboBox<ComboItem> getCbClientes() { return cbClientes; }
    public JButton getBtnRefrescarCombo() { return btnRefrescarCombo; }
    public JTextField getTxtPlacas() { return txtPlacas; }
    public JTextField getTxtMarca() { return txtMarca; }
    public JTextField getTxtModelo() { return txtModelo; }
    public JTextField getTxtAnio() { return txtAnio; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JTable getTablaVehiculos() { return tablaVehiculos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
}