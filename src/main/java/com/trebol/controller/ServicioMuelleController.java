package com.trebol.controller;

import com.trebol.dao.ServicioMuelleDAO;
import com.trebol.model.ServicioMuelle;
import com.trebol.view.PanelMuelles;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServicioMuelleController {
    
    private PanelMuelles vista;
    private ServicioMuelleDAO dao;

    public ServicioMuelleController(PanelMuelles vista, ServicioMuelleDAO dao) {
        this.vista = vista;
        this.dao = dao;
        
        // 1. "Escuchar" el clic del botón guardar
        this.vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarServicio();
            }
        });
    }

    // 2. Lógica para guardar el servicio
    private void registrarServicio() {
        try {
            // A. Extraer los datos de las cajas de texto de la vista
            int idOrden = Integer.parseInt(vista.getTxtIdOrden().getText().trim());
            String tipo = vista.getCbTipoReparacion().getSelectedItem().toString();
            String detalles = vista.getTxtDetalles().getText().trim();
            String piezas = vista.getTxtPiezas().getText().trim();
            String tecnico = vista.getTxtTecnico().getText().trim();

            // Validar que los campos no estén vacíos
            if (detalles.isEmpty() || tecnico.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, llena los campos de detalles y técnico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // B. Empaquetarlos en el Modelo
            ServicioMuelle servicio = new ServicioMuelle(0, idOrden, tipo, detalles, piezas, tecnico);

            // C. Enviarlos al DAO para guardarlos en MariaDB
            if (dao.registrarServicio(servicio)) {
                JOptionPane.showMessageDialog(vista, "¡Servicio registrado exitosamente en la base de datos!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar en la base de datos. Verifica la conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El ID de la Orden debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
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
}