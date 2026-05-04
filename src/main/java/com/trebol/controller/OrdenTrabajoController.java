package com.trebol.controller;

import com.trebol.dao.OrdenTrabajoDAO;
import com.trebol.dao.VehiculoDAO;
import com.trebol.model.OrdenTrabajo;
import com.trebol.model.Vehiculo;
import com.trebol.view.PanelOrdenes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrdenTrabajoController implements ActionListener {

    private PanelOrdenes vista;
    private OrdenTrabajoDAO dao;
    private VehiculoDAO vehiculoDao;
    private List<Vehiculo> listaVehiculosCache; // Para guardar los vehículos temporalmente y sacar su ID

    public OrdenTrabajoController(PanelOrdenes vista, OrdenTrabajoDAO dao, VehiculoDAO vehiculoDao) {
        this.vista = vista;
        this.dao = dao;
        this.vehiculoDao = vehiculoDao;

        // Agregar el "escuchador" de clics al botón
        this.vista.getBtnGenerarOrden().addActionListener(this);

        // Cargar los datos iniciales
        cargarVehiculosEnComboBox();
        listarOrdenesEnTabla();
    }

    private void cargarVehiculosEnComboBox() {
        listaVehiculosCache = vehiculoDao.listarVehiculos();
        vista.getCbPlacaVehiculo().removeAllItems();
        vista.getCbPlacaVehiculo().addItem("Seleccionar Vehículo...");
        
        for (Vehiculo v : listaVehiculosCache) {
            vista.getCbPlacaVehiculo().addItem(v.getPlacas());
        }
    }

    private void listarOrdenesEnTabla() {
        vista.getModeloTabla().setRowCount(0); // Limpiar tabla
        List<OrdenTrabajo> lista = dao.listarOrdenes();
        
        for (OrdenTrabajo o : lista) {
            Object[] fila = new Object[6];
            fila[0] = o.getIdOrden();
            fila[1] = o.getPlacaVehiculo();
            fila[2] = o.getFechaIngreso();
            fila[3] = o.getEstatus();
            fila[4] = "$" + o.getCostoManoObra();
            fila[5] = "$" + o.getCostoRefacciones();
            vista.getModeloTabla().addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGenerarOrden()) {
            registrarNuevaOrden();
        }
    }

    private void registrarNuevaOrden() {
        // 1. Validar que se haya seleccionado un vehículo
        if (vista.getCbPlacaVehiculo().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un vehículo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener el vehículo seleccionado del caché para sacar su ID de Vehículo y de Cliente
        String placaSeleccionada = vista.getCbPlacaVehiculo().getSelectedItem().toString();
        Vehiculo vehiculoSeleccionado = null;
        for (Vehiculo v : listaVehiculosCache) {
            if (v.getPlacas().equals(placaSeleccionada)) {
                vehiculoSeleccionado = v;
                break;
            }
        }

        if (vehiculoSeleccionado == null) return; // Seguridad extra

        // 3. Capturar y validar los datos del formulario
        String descripcion = vista.getTxtDescripcionProblema().getText();
        String estatus = vista.getCbEstatus().getSelectedItem().toString();
        
        double costoManoObra = 0.0;
        double costoRefacciones = 0.0;

        try {
            costoManoObra = Double.parseDouble(vista.getTxtCostoManoObra().getText().trim());
            costoRefacciones = Double.parseDouble(vista.getTxtCostoRefacciones().getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Los costos deben ser valores numéricos válidos (ej. 1500.50).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Crear el objeto modelo y mandarlo al DAO
        OrdenTrabajo orden = new OrdenTrabajo(
                vehiculoSeleccionado.getIdCliente(),
                vehiculoSeleccionado.getIdVehiculo(),
                estatus,
                descripcion,
                costoManoObra,
                costoRefacciones
        );

        if (dao.registrarOrden(orden)) {
            JOptionPane.showMessageDialog(vista, "¡Orden de Trabajo generada con éxito!");
            limpiarCampos();
            listarOrdenesEnTabla(); // Refrescar la tabla
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar la orden de trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        vista.getCbPlacaVehiculo().setSelectedIndex(0);
        vista.getTxtDescripcionProblema().setText("");
        vista.getTxtCostoManoObra().setText("0.00");
        vista.getTxtCostoRefacciones().setText("0.00");
        vista.getCbEstatus().setSelectedIndex(0);
    }
}