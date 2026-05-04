package com.trebol.controller;

import com.trebol.dao.OrdenTrabajoDAO;
import com.trebol.dao.VehiculoDAO;
import com.trebol.model.OrdenTrabajo;
import com.trebol.model.Vehiculo;
import com.trebol.view.PanelOrdenes;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class OrdenTrabajoController implements ActionListener {

    private PanelOrdenes vista;
    private OrdenTrabajoDAO dao;
    private VehiculoDAO vehiculoDao;
    private List<Vehiculo> listaVehiculosCache;
    private int idOrdenSeleccionada = -1; // Para saber qué orden estamos editando

    public OrdenTrabajoController(PanelOrdenes vista, OrdenTrabajoDAO dao, VehiculoDAO vehiculoDao) {
        this.vista = vista;
        this.dao = dao;
        this.vehiculoDao = vehiculoDao;

        // Registro de Listeners
        this.vista.getBtnGenerarOrden().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);

        // Listener para seleccionar fila de la tabla
        this.vista.getTablaOrdenes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

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
        vista.getModeloTabla().setRowCount(0);
        List<OrdenTrabajo> lista = dao.listarOrdenes();
        
        for (OrdenTrabajo o : lista) {
            Object[] fila = {
                o.getIdOrden(),
                o.getPlacaVehiculo(),
                o.getFechaIngreso(),
                o.getEstatus(),
                "$" + o.getCostoManoObra(),
                "$" + o.getCostoRefacciones()
            };
            vista.getModeloTabla().addRow(fila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGenerarOrden()) {
            registrarNuevaOrden();
        } else if (e.getSource() == vista.getBtnActualizar()) {
            actualizarOrden();
        }
    }

    private void registrarNuevaOrden() {
        if (vista.getCbPlacaVehiculo().getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vehículo.");
            return;
        }

        try {
            OrdenTrabajo orden = extraerDatosDeVista();
            if (dao.registrarOrden(orden)) {
                JOptionPane.showMessageDialog(vista, "Orden generada con éxito.");
                limpiarCampos();
                listarOrdenesEnTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void actualizarOrden() {
        if (idOrdenSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione una orden de la tabla para actualizar.");
            return;
        }

        try {
            OrdenTrabajo orden = extraerDatosDeVista();
            orden.setIdOrden(idOrdenSeleccionada);
            
            if (dao.actualizarOrden(orden)) {
                JOptionPane.showMessageDialog(vista, "Orden actualizada correctamente.");
                limpiarCampos();
                listarOrdenesEnTabla();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTablaOrdenes().getSelectedRow();
        if (fila != -1) {
            idOrdenSeleccionada = (int) vista.getModeloTabla().getValueAt(fila, 0);
            
            // Buscamos la orden completa en la base de datos a través del ID o la lista
            // Para simplificar, buscaremos la placa en el combo
            String placa = vista.getModeloTabla().getValueAt(fila, 1).toString();
            vista.getCbPlacaVehiculo().setSelectedItem(placa);
            
            // Como la tabla no tiene todos los campos (como la descripción), 
            // lo ideal es traer el objeto completo del DAO
            List<OrdenTrabajo> listaActual = dao.listarOrdenes();
            for (OrdenTrabajo o : listaActual) {
                if (o.getIdOrden() == idOrdenSeleccionada) {
                    vista.getTxtDescripcionProblema().setText(o.getDescripcionProblema());
                    vista.getTxtCostoManoObra().setText(String.valueOf(o.getCostoManoObra()));
                    vista.getTxtCostoRefacciones().setText(String.valueOf(o.getCostoRefacciones()));
                    vista.getCbEstatus().setSelectedItem(o.getEstatus());
                    break;
                }
            }
        }
    }

    private OrdenTrabajo extraerDatosDeVista() {
        String placaSeleccionada = vista.getCbPlacaVehiculo().getSelectedItem().toString();
        Vehiculo vSel = null;
        for (Vehiculo v : listaVehiculosCache) {
            if (v.getPlacas().equals(placaSeleccionada)) {
                vSel = v;
                break;
            }
        }

        if (vSel == null) throw new RuntimeException("Vehículo no encontrado.");

        OrdenTrabajo o = new OrdenTrabajo();
        o.setIdCliente(vSel.getIdCliente());
        o.setIdVehiculo(vSel.getIdVehiculo());
        o.setEstatus(vista.getCbEstatus().getSelectedItem().toString());
        o.setDescripcionProblema(vista.getTxtDescripcionProblema().getText());
        o.setCostoManoObra(Double.parseDouble(vista.getTxtCostoManoObra().getText().trim()));
        o.setCostoRefacciones(Double.parseDouble(vista.getTxtCostoRefacciones().getText().trim()));
        // Campos adicionales del DAO que no están en tu panel actual (los mandamos vacíos)
        o.setFechaEntregaEstimada(""); 
        o.setDiagnosticoTecnico("");

        return o;
    }

    private void limpiarCampos() {
        vista.getCbPlacaVehiculo().setSelectedIndex(0);
        vista.getTxtDescripcionProblema().setText("");
        vista.getTxtCostoManoObra().setText("0.00");
        vista.getTxtCostoRefacciones().setText("0.00");
        vista.getCbEstatus().setSelectedIndex(0);
        idOrdenSeleccionada = -1;
    }
}