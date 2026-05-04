package com.trebol.view;

import com.trebol.utils.ComboItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVehiculos extends JPanel {
    private JComboBox<ComboItem> cbClientes; 
    private JButton btnRefrescarCombo; // Nuevo botón
    private JTextField txtPlacas, txtMarca, txtModelo, txtAnio;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;

    public PanelVehiculos() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));

        // --- PANEL DE FORMULARIO ---
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Vehículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicializamos ComboBox y el botón de refrescar
        cbClientes = new JComboBox<>();
        btnRefrescarCombo = new JButton("🔄");
        btnRefrescarCombo.setToolTipText("Actualizar lista de clientes");

        // Panel especial para juntar el combo y el botón en la misma celda
        JPanel panelCombo = new JPanel(new BorderLayout(5, 0));
        panelCombo.setBackground(Color.WHITE);
        panelCombo.add(cbClientes, BorderLayout.CENTER);
        panelCombo.add(btnRefrescarCombo, BorderLayout.EAST);

        txtPlacas = new JTextField(15);
        txtMarca = new JTextField(15);
        txtModelo = new JTextField(15);
        txtAnio = new JTextField(15);

        // Fila 0: Cliente con su botón de refrescar
        colocarComponente(panelForm, new JLabel("Cliente:"), 0, 0, gbc);
        colocarComponente(panelForm, panelCombo, 1, 0, gbc);

        // Fila 1 a 4: Resto de campos
        colocarComponente(panelForm, new JLabel("Placas:"), 0, 1, gbc);
        colocarComponente(panelForm, txtPlacas, 1, 1, gbc);
        colocarComponente(panelForm, new JLabel("Marca:"), 0, 2, gbc);
        colocarComponente(panelForm, txtMarca, 1, 2, gbc);
        colocarComponente(panelForm, new JLabel("Modelo:"), 0, 3, gbc);
        colocarComponente(panelForm, txtModelo, 1, 3, gbc);
        colocarComponente(panelForm, new JLabel("Año:"), 0, 4, gbc);
        colocarComponente(panelForm, txtAnio, 1, 4, gbc);

        // --- PANEL DE BOTONES ACCIÓN ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setForeground(Color.WHITE);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(52, 152, 219));
        btnActualizar.setForeground(Color.WHITE);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panelForm.add(panelBotones, gbc);

        add(panelForm, BorderLayout.NORTH);

        // --- PANEL DE TABLA ---
        modeloTabla = new DefaultTableModel(
            new String[]{"ID Vehículo", "ID Cliente", "Placas", "Marca", "Modelo", "Año"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
    }

    private void colocarComponente(JPanel p, JComponent c, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        p.add(c, gbc);
    }

    // Getters
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





























