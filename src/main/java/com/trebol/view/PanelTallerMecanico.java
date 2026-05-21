package com.trebol.view;

import com.trebol.dao.ProductoDAO;
import com.trebol.model.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelTallerMecanico extends JPanel {

    private JTextField txtBuscar;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private ProductoDAO productoDAO;
    private JButton btnAgregarAServicio, btnRefrescar, btnNuevaOrden;

    public PanelTallerMecanico() {
        productoDAO = new ProductoDAO();
        initComponents();
        cargarProductos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));

        // ==================== PANEL SUPERIOR ====================
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBackground(new Color(240, 240, 240));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("🛠️ TALLER MECÁNICO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 102, 204));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(25);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarProductos();
            }
        });

        btnRefrescar = new JButton("🔄 Actualizar");
        btnNuevaOrden = new JButton("➕ Nueva Orden");

        panelBusqueda.add(new JLabel("Buscar servicio:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnRefrescar);
        panelBusqueda.add(btnNuevaOrden);

        panelSuperior.add(lblTitulo, BorderLayout.WEST);
        panelSuperior.add(panelBusqueda, BorderLayout.CENTER);

        // ==================== TABLA ====================
        String[] columnas = {"ID", "Servicio", "Descripción", "Precio", "Categoría", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setRowHeight(28);
        tablaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaProductos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        // ==================== BOTONES INFERIORES ====================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAgregarAServicio = new JButton("➕ Agregar a Orden Actual");
        btnAgregarAServicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregarAServicio.setBackground(new Color(0, 153, 76));
        btnAgregarAServicio.setForeground(Color.WHITE);

        panelBotones.add(btnAgregarAServicio);

        // Agregar todo al panel
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        btnAgregarAServicio.addActionListener(e -> agregarAServicio());
        btnRefrescar.addActionListener(e -> cargarProductos());
        btnNuevaOrden.addActionListener(e -> nuevaOrden());
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        List<Producto> productos = productoDAO.obtenerPorCategoria("Mecánica");

        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                "$ " + String.format("%,.2f", p.getPrecio()),
                p.getCategoria(),
                p.getStock()
            });
        }
    }

    private void filtrarProductos() {
        // Implementación básica de filtro
        String texto = txtBuscar.getText().toLowerCase().trim();
        modeloTabla.setRowCount(0);
        
        List<Producto> productos = productoDAO.obtenerPorCategoria("Mecánica");
        
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto) || 
                p.getDescripcion().toLowerCase().contains(texto)) {
                
                modeloTabla.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    "$ " + String.format("%,.2f", p.getPrecio()),
                    p.getCategoria(),
                    p.getStock()
                });
            }
        }
    }

    private void agregarAServicio() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idProducto = (int) modeloTabla.getValueAt(fila, 0);
        String nombre = (String) modeloTabla.getValueAt(fila, 1);
        double precio = Double.parseDouble(modeloTabla.getValueAt(fila, 3).toString().replace("$ ", "").replace(",", ""));

        // Aquí puedes llamar a la orden actual o abrir diálogo
        JOptionPane.showMessageDialog(this, 
            "Servicio agregado:\n" + nombre + "\nPrecio: $" + precio, 
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // TODO: Integrar con PanelOrdenes
    }

    private void nuevaOrden() {
        // Abrir panel de órdenes o diálogo
        JOptionPane.showMessageDialog(this, "Funcionalidad Nueva Orden en desarrollo...", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
