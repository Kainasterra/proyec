package com.trebol.controller;

import com.trebol.dao.VehiculoDAO;
import com.trebol.model.Vehiculo;
import com.trebol.view.PanelVehiculos;
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

        // Escuchar botones
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);

        // Escuchar clics en la tabla
        this.vista.getTablaVehiculos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        listar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) registrar();
        if (e.getSource() == vista.getBtnActualizar()) actualizar();
        if (e.getSource() == vista.getBtnEliminar()) eliminar();
    }

    private void registrar() {
        try {
            Vehiculo v = extraerDatos();
            if (dao.registrarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado correctamente.");
                limpiarYCargar();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error: ID Cliente y Año deben ser números.");
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
                JOptionPane.showMessageDialog(vista, "Datos del vehículo actualizados.");
                limpiarYCargar();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error: ID Cliente y Año deben ser números.");
        }
    }

    private void eliminar() {
        if (idVehiculoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo para eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar este vehículo?", "Confirmar", JOptionPane.YES_NO_OPTION);
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
            vista.getTxtIdCliente().setText(vista.getModeloTabla().getValueAt(fila, 1).toString());
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
        return new Vehiculo(
            Integer.parseInt(vista.getTxtIdCliente().getText()),
            vista.getTxtPlacas().getText(),
            vista.getTxtMarca().getText(),
            vista.getTxtModelo().getText(),
            Integer.parseInt(vista.getTxtAnio().getText())
        );
    }

    private void limpiarYCargar() {
        vista.getTxtIdCliente().setText("");
        vista.getTxtPlacas().setText("");
        vista.getTxtMarca().setText("");
        vista.getTxtModelo().setText("");
        vista.getTxtAnio().setText("");
        idVehiculoSeleccionado = -1;
        vista.getTablaVehiculos().clearSelection();
        listar();
    }
}