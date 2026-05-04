package com.trebol.controller;

import com.trebol.dao.VehiculoDAO;
import com.trebol.dao.ClienteDAO;
import com.trebol.model.Vehiculo;
import com.trebol.model.Cliente;
import com.trebol.view.PanelVehiculos;
import com.trebol.utils.ComboItem;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VehiculoController implements ActionListener {
    private PanelVehiculos vista;
    private VehiculoDAO dao;
    private int idVehiculoSeleccionado = -1;

    public VehiculoController(PanelVehiculos vista, VehiculoDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // Listeners de botones
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnRefrescarCombo().addActionListener(this);

        // --- NUEVO: Listener para el Buscador ---
        this.vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrar();
            }
        });

        // Listener para la tabla
        this.vista.getTablaVehiculos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        cargarComboClientes();
        listar();
    }

    private void filtrar() {
        String texto = vista.getTxtBuscar().getText();
        // Filtra por cualquier columna (Placas, Marca, Modelo, etc.)
        vista.getSorter().setRowFilter(RowFilter.regexFilter("(?i)" + texto));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) registrar();
        if (e.getSource() == vista.getBtnActualizar()) actualizar();
        if (e.getSource() == vista.getBtnEliminar()) eliminar();
        if (e.getSource() == vista.getBtnRefrescarCombo()) {
            cargarComboClientes();
            JOptionPane.showMessageDialog(vista, "Lista de clientes actualizada.");
        }
    }

    private void registrar() {
        try {
            Vehiculo v = extraerDatos();
            if (dao.registrarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado.");
                limpiarYCargar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void actualizar() {
        if (idVehiculoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo de la tabla.");
            return;
        }
        try {
            Vehiculo v = extraerDatos();
            v.setIdVehiculo(idVehiculoSeleccionado);
            if (dao.actualizarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo actualizado.");
                limpiarYCargar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (idVehiculoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar vehículo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarVehiculo(idVehiculoSeleccionado)) {
                limpiarYCargar();
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTablaVehiculos().getSelectedRow();
        if (fila != -1) {
            // CRÍTICO: Convertir el índice de la vista al modelo por si hay filtro activo
            int filaModelo = vista.getTablaVehiculos().convertRowIndexToModel(fila);
            
            idVehiculoSeleccionado = (int) vista.getModeloTabla().getValueAt(filaModelo, 0);
            int idClienteEnTabla = (int) vista.getModeloTabla().getValueAt(filaModelo, 1);
            
            // Sincronizar ComboBox
            for (int i = 0; i < vista.getCbClientes().getItemCount(); i++) {
                ComboItem item = vista.getCbClientes().getItemAt(i);
                if (item.getId() == idClienteEnTabla) {
                    vista.getCbClientes().setSelectedIndex(i);
                    break;
                }
            }
            
            vista.getTxtPlacas().setText(vista.getModeloTabla().getValueAt(filaModelo, 2).toString());
            vista.getTxtMarca().setText(vista.getModeloTabla().getValueAt(filaModelo, 3).toString());
            vista.getTxtModelo().setText(vista.getModeloTabla().getValueAt(filaModelo, 4).toString());
            vista.getTxtAnio().setText(vista.getModeloTabla().getValueAt(filaModelo, 5).toString());
        }
    }

    private void listar() {
        vista.getModeloTabla().setRowCount(0);
        List<Vehiculo> lista = dao.listarVehiculos();
        for (Vehiculo v : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                v.getIdVehiculo(), v.getIdCliente(), v.getPlacas(), v.getMarca(), v.getModelo(), v.getAnio()
            });
        }
    }

    private void cargarComboClientes() {
        vista.getCbClientes().removeAllItems();
        ClienteDAO cDao = new ClienteDAO();
        List<Cliente> clientes = cDao.listarClientes();
        for (Cliente c : clientes) {
            vista.getCbClientes().addItem(new ComboItem(c.getIdCliente(), c.getNombre()));
        }
    }

    private Vehiculo extraerDatos() {
        ComboItem item = (ComboItem) vista.getCbClientes().getSelectedItem();
        if (item == null) throw new RuntimeException("Seleccione un cliente.");
        
        return new Vehiculo(
            item.getId(),
            vista.getTxtPlacas().getText(),
            vista.getTxtMarca().getText(),
            vista.getTxtModelo().getText(),
            Integer.parseInt(vista.getTxtAnio().getText())
        );
    }

    private void limpiarYCargar() {
        if (vista.getCbClientes().getItemCount() > 0) vista.getCbClientes().setSelectedIndex(0);
        vista.getTxtPlacas().setText("");
        vista.getTxtMarca().setText("");
        vista.getTxtModelo().setText("");
        vista.getTxtAnio().setText("");
        vista.getTxtBuscar().setText(""); // Limpiar buscador
        vista.getSorter().setRowFilter(null); // Quitar filtro
        idVehiculoSeleccionado = -1;
        listar();
    }
}