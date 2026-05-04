package com.trebol.controller;

import com.trebol.dao.ServicioMuelleDAO;
import com.trebol.dao.InventarioDAO; // IMPORTANTE
import com.trebol.model.ServicioMuelle;
import com.trebol.view.PanelMuelles;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicioMuelleController {
    
    private PanelMuelles vista;
    private ServicioMuelleDAO dao;
    private InventarioDAO inventarioDao; // Nueva dependencia

    // Actualizamos el constructor para recibir el InventarioDAO
    public ServicioMuelleController(PanelMuelles vista, ServicioMuelleDAO dao, InventarioDAO inventarioDao) {
        this.vista = vista;
        this.dao = dao;
        this.inventarioDao = inventarioDao;
        
        cargarTabla();

        this.vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarServicio();
            }
        });
    }

    private void registrarServicio() {
        try {
            int idOrden = Integer.parseInt(vista.getTxtIdOrden().getText().trim());
            String tipo = vista.getCbTipoReparacion().getSelectedItem().toString();
            String detalles = vista.getTxtDetalles().getText().trim();
            String idPiezaStr = vista.getTxtPiezas().getText().trim(); // ID de la refacción
            String tecnico = vista.getTxtTecnico().getText().trim();

            if (detalles.isEmpty() || tecnico.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Llene los campos obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ServicioMuelle servicio = new ServicioMuelle(0, idOrden, tipo, detalles, idPiezaStr, tecnico);

            // 1. Intentamos registrar el servicio en Muelles
            if (dao.registrarServicio(servicio)) {
                
                // 2. LÓGICA DE INVENTARIO: Si se especificó una pieza, la descontamos
                if (!idPiezaStr.isEmpty()) {
                    try {
                        int idPieza = Integer.parseInt(idPiezaStr);
                        // Descontamos 1 unidad por defecto
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

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID de Orden debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método auxiliar para limpiar el formulario después de guardar
    private void limpiarCampos() {
        vista.getTxtIdOrden().setText("");
        vista.getCbTipoReparacion().setSelectedIndex(0);
        vista.getTxtDetalles().setText("");
        vista.getTxtPiezas().setText("");
        vista.getTxtTecnico().setText("");
    }
    // Método para llenar la tabla de la vista con los datos de la base
    private void cargarTabla() {
        // 1. Limpiamos la tabla por si tenía datos viejos
        vista.getModeloTabla().setRowCount(0); 
        
        // 2. Pedimos los datos al DAO
        java.util.List<ServicioMuelle> lista = dao.listarTodos();
        
        // 3. Recorremos la lista y agregamos fila por fila
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