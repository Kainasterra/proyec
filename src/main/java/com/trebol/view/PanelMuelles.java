package com.trebol.view;

import com.trebol.utils.ComboItem; // Importación clave para la integración
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMuelles extends JPanel {

    // Componentes del formulario
    private JComboBox<ComboItem> cbOrdenesTrabajo; // Cambiado: De JTextField a JComboBox
    private JComboBox<String> cbTipoReparacion;
    private JTextField txtDetalles;
    private JTextField txtPiezas;
    private JTextField txtTecnico;
    private JButton btnGuardar;
    private JButton btnActualizarLista; // Añadido: Para refrescar si crean una orden nueva
    
    // Componentes de la tabla
    private JTable tablaServicios;
    private DefaultTableModel modeloTabla;

    public PanelMuelles() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Módulo de Suspensión y Muelles");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 64, 83));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Fila 1: Integración con Orden de Trabajo
        panelFormulario.add(new JLabel("Seleccionar Orden de Trabajo:"));
        JPanel panelComboOrden = new JPanel(new BorderLayout(5,0));
        panelComboOrden.setBackground(Color.WHITE);
        cbOrdenesTrabajo = new JComboBox<>();
        btnActualizarLista = new JButton("🔄");
        panelComboOrden.add(cbOrdenesTrabajo, BorderLayout.CENTER);
        panelComboOrden.add(btnActualizarLista, BorderLayout.EAST);
        panelFormulario.add(panelComboOrden);

        // Resto de campos
        panelFormulario.add(new JLabel("Tipo de Reparación:"));
        String[] opcionesReparacion = {"Cambio de Hojas", "Alineación", "Engrase", "Cambio de Bujes", "Otro"};
        cbTipoReparacion = new JComboBox<>(opcionesReparacion);
        panelFormulario.add(cbTipoReparacion);

        panelFormulario.add(new JLabel("Detalles del Ajuste:"));
        txtDetalles = new JTextField();
        panelFormulario.add(txtDetalles);

        panelFormulario.add(new JLabel("ID Piezas/Refacciones (Inventario):"));
        txtPiezas = new JTextField();
        panelFormulario.add(txtPiezas);

        panelFormulario.add(new JLabel("Técnico Responsable:"));
        txtTecnico = new JTextField();
        panelFormulario.add(txtTecnico);

        panelFormulario.add(new JLabel("")); 
        btnGuardar = new JButton("Registrar Servicio");
        btnGuardar.setBackground(new Color(46, 204, 113)); 
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(btnGuardar);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID Muelle", "ID Orden", "Tipo Reparación", "Técnico"};
        modeloTabla = new DefaultTableModel(columnas, 0); 
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.setRowHeight(25);
        tablaServicios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollTabla = new JScrollPane(tablaServicios);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    // --- GETTERS ACTUALIZADOS ---
    public JComboBox<ComboItem> getCbOrdenesTrabajo() { return cbOrdenesTrabajo; }
    public JButton getBtnActualizarLista() { return btnActualizarLista; }
    public JComboBox<String> getCbTipoReparacion() { return cbTipoReparacion; }
    public JTextField getTxtDetalles() { return txtDetalles; }
    public JTextField getTxtPiezas() { return txtPiezas; }
    public JTextField getTxtTecnico() { return txtTecnico; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JTable getTablaServicios() { return tablaServicios; }
}