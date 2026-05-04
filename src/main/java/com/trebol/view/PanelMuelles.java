package com.trebol.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelMuelles extends JPanel {

    // Componentes del formulario
    private JTextField txtIdOrden;
    private JComboBox<String> cbTipoReparacion;
    private JTextField txtDetalles;
    private JTextField txtPiezas;
    private JTextField txtTecnico;
    private JButton btnGuardar;
    
    // Componentes de la tabla
    private JTable tablaServicios;
    private DefaultTableModel modeloTabla;

    public PanelMuelles() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Configuración principal del panel
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes internos

        // 1. Encabezado del panel
        JLabel lblTitulo = new JLabel("Módulo de Suspensión y Muelles");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 64, 83));
        add(lblTitulo, BorderLayout.NORTH);

        // --- CONTENEDOR CENTRAL (Formulario + Tabla) ---
        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
        panelCentro.setBackground(new Color(245, 245, 245));

        // 2. Construcción del Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Añadimos las etiquetas y las cajas de texto
        panelFormulario.add(new JLabel("ID Orden de Trabajo vinculada:"));
        txtIdOrden = new JTextField();
        panelFormulario.add(txtIdOrden);

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

        panelFormulario.add(new JLabel("")); // Espacio en blanco para empujar el botón a la derecha
        btnGuardar = new JButton("Registrar Servicio");
        btnGuardar.setBackground(new Color(46, 204, 113)); // Verde esmeralda
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(btnGuardar);

        // Agregamos el formulario a la parte superior del contenedor central
        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // 3. Construcción de la Tabla de Historial
        String[] columnas = {"ID Muelle", "ID Orden", "Tipo Reparación", "Técnico"};
        modeloTabla = new DefaultTableModel(columnas, 0); // 0 filas iniciales
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.setRowHeight(25);
        tablaServicios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // JScrollPane es necesario para que la tabla tenga barras de desplazamiento
        JScrollPane scrollTabla = new JScrollPane(tablaServicios);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        // Finalmente, agregamos el contenedor central al panel principal
        add(panelCentro, BorderLayout.CENTER);
    }
}