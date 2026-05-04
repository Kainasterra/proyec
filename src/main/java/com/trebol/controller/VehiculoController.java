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

        // --- Registro de Listeners ---
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnRefrescarCombo().addActionListener(this); // Nuevo listener

        this.vista.getTablaVehiculos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        // Carga inicial
        cargarComboClientes();
        listar();
    }

    private void cargarComboClientes() {
        vista.getCbClientes().removeAllItems();
        ClienteDAO cDao = new ClienteDAO();
        List<Cliente> lista = cDao.listarClientes();
        for (Cliente c : lista) {
            vista.getCbClientes().addItem(new ComboItem(c.getIdCliente(), c.getNombre()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) registrar();
        if (e.getSource() == vista.getBtnActualizar()) actualizar();
        if (e.getSource() == vista.getBtnEliminar()) eliminar();
        
        // Acción del botón refrescar
        if (e.getSource() == vista.getBtnRefrescarCombo()) {
            cargarComboClientes();
            JOptionPane.showMessageDialog(vista, "Lista de clientes sincronizada.");
        }
    }

    private void registrar() {
        try {
            Vehiculo v = extraerDatos();
            if (dao.registrarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado con éxito.");
                limpiarYCargar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al registrar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        if (idVehiculoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo de la tabla para editar.");
            return;
        }
        try {
            Vehiculo v = extraerDatos();
            v.setIdVehiculo(idVehiculoSeleccionado);
            if (dao.actualizarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo actualizado correctamente.");
                limpiarYCargar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (idVehiculoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo para eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Deseas eliminar este vehículo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarVehiculo(idVehiculoSeleccionado)) {
                limpiarYCargar();
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTablaVehiculos().getSelectedRow();
        if (fila != -1) {
            idVehiculoSeleccionado = (int) vista.getModeloTabla().getValueAt(fila, 0);
            int idClienteEnTabla = (int) vista.getModeloTabla().getValueAt(fila, 1);
            
            // Sincronizar el ComboBox con el ID de la tabla
            for (int i = 0; i < vista.getCbClientes().getItemCount(); i++) {
                ComboItem item = vista.getCbClientes().getItemAt(i);
                if (item.getId() == idClienteEnTabla) {
                    vista.getCbClientes().setSelectedIndex(i);
                    break;
                }
            }
            
            vista.getTxtPlacas().setText(vista.getModeloTabla().getValueAt(fila, 2).toString());
            vista.getTxtMarca().setText(vista.getModeloTabla().getValueAt(fila, 3).toString());
            vista.getTxtModelo().setText(vista.getModeloTabla().getValueAt(fila, 4).toString());
            vista.getTxtAnio().setText(vista.getModeloTabla().getValueAt(fila, 5).toString());
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

    private Vehiculo extraerDatos() {
        ComboItem item = (ComboItem) vista.getCbClientes().getSelectedItem();
        if (item == null) throw new RuntimeException("Selecciona un cliente válido.");

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
        idVehiculoSeleccionado = -1;
        vista.getTablaVehiculos().clearSelection();
        cargarComboClientes();
        listar();
    }
}