package com.trebol.controller;

import com.trebol.dao.InventarioDAO;
import com.trebol.dao.OrdenTrabajoDAO;
import com.trebol.dao.ServicioMuelleDAO;
import com.trebol.model.OrdenTrabajo;
import com.trebol.model.ServicioMuelle;
import com.trebol.utils.ComboItem;
import com.trebol.view.PanelMuelles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ServicioMuelleController implements ActionListener {
    
    private PanelMuelles vista;
    private ServicioMuelleDAO dao;
    private InventarioDAO inventarioDao; 
    private OrdenTrabajoDAO ordenDao; // Necesario para llenar el ComboBox

    public ServicioMuelleController(PanelMuelles vista, ServicioMuelleDAO dao, InventarioDAO inventarioDao, OrdenTrabajoDAO ordenDao) {
        this.vista = vista;
        this.dao = dao;
        this.inventarioDao = inventarioDao;
        this.ordenDao = ordenDao;
        
        // Registrar listeners
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizarLista().addActionListener(this);

        // Cargar datos iniciales
        cargarOrdenesEnComboBox();
        cargarTabla();
    }

    private void cargarOrdenesEnComboBox() {
        vista.getCbOrdenesTrabajo().removeAllItems();
        vista.getCbOrdenesTrabajo().addItem(new ComboItem(0, "Seleccionar Orden..."));

        List<OrdenTrabajo> listaOrdenes = ordenDao.listarOrdenes();

        for (OrdenTrabajo orden : listaOrdenes) {
            if (!orden.getEstatus().equalsIgnoreCase("Entregado")) {
                String textoVisible = "Orden #" + orden.getIdOrden() + " - Placa: " + orden.getPlacaVehiculo();
                vista.getCbOrdenesTrabajo().addItem(new ComboItem(orden.getIdOrden(), textoVisible));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            registrarServicio();
        } else if (e.getSource() == vista.getBtnActualizarLista()) {
            cargarOrdenesEnComboBox();
            JOptionPane.showMessageDialog(vista, "Lista de Órdenes actualizada.", "Actualización", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void registrarServicio() {
        // 1. Validar que se haya seleccionado una orden desde el ComboBox
        if (vista.getCbOrdenesTrabajo().getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una Orden de Trabajo válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Extraer el ID de la orden usando ComboItem
            ComboItem itemSeleccionado = (ComboItem) vista.getCbOrdenesTrabajo().getSelectedItem();
            int idOrden = itemSeleccionado.getId();

            String tipo = vista.getCbTipoReparacion().getSelectedItem().toString();
            String detalles = vista.getTxtDetalles().getText().trim();
            String idPiezaStr = vista.getTxtPiezas().getText().trim(); 
            String tecnico = vista.getTxtTecnico().getText().trim();

            if (detalles.isEmpty() || tecnico.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Llene los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ServicioMuelle servicio = new ServicioMuelle(0, idOrden, tipo, detalles, idPiezaStr, tecnico);

            // 2. Intentamos registrar el servicio en Muelles
            if (dao.registrarServicio(servicio)) {
                
                // 3. LÓGICA DE INVENTARIO: Descontar pieza
                if (!idPiezaStr.isEmpty()) {
                    try {
                        int idPieza = Integer.parseInt(idPiezaStr);
                        if (inventarioDao.descontarStock(idPieza, 1)) {
                            JOptionPane.showMessageDialog(vista, "Servicio registrado y pieza descontada del inventario.");
                        } else {
                            JOptionPane.showMessageDialog(vista, "Servicio registrado, pero NO se pudo descontar la pieza (Stock insuficiente o ID inválido).", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(vista, "ID de pieza inválido. No se descontó del inventario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Servicio registrado exitosamente.");
                }

                limpiarCampos();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el servicio.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        vista.getCbOrdenesTrabajo().setSelectedIndex(0);
        vista.getCbTipoReparacion().setSelectedIndex(0);
        vista.getTxtDetalles().setText("");
        vista.getTxtPiezas().setText("");
        vista.getTxtTecnico().setText("");
    }

    private void cargarTabla() {
        vista.getModeloTabla().setRowCount(0); 
        List<ServicioMuelle> lista = dao.listarTodos();
        
        for (ServicioMuelle s : lista) {
            Object[] fila = {
                s.getIdMuelle(),
                s.getIdOrden(),
                s.getTipoReparacion(),
                s.getTecnicoResponsable()
            };
            vista.getModeloTabla().addRow(fila);
        }
    }
}