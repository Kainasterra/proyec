package com.trebol.controller;

import com.trebol.dao.CatalogosDAO;
import com.trebol.dao.VehiculoDAO;
import com.trebol.model.Marca;
import com.trebol.model.Modelo;
import com.trebol.model.Vehiculo;
import com.trebol.view.PanelVehiculos;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VehiculoController {
    
    private PanelVehiculos vista;
    private VehiculoDAO dao;

    public VehiculoController(PanelVehiculos vista, VehiculoDAO dao) {
        this.vista = vista;
        this.dao = dao;
        
        configurarComboBoxes(); // Iniciamos la lógica en cascada
        cargarTabla();

        this.vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVehiculo();
            }
        });
    }

    // --- LÓGICA DE COMBOBOXES EN CASCADA ---

    // Reemplaza tus métodos de configuración en VehiculoController.java:

private void configurarComboBoxes() {
    CatalogosDAO catDAO = new CatalogosDAO();
    
    // 1. Cargar Marcas desde la BD
    vista.getCbMarca().removeAllItems();
    vista.getCbMarca().addItem(null); // Opción vacía
    for (Marca m : catDAO.listarMarcas()) {
        vista.getCbMarca().addItem(m.getNombre()); // Aquí podrías meter el objeto Marca si quisieras más nivel
    }

    // 2. Lógica en cascada para Modelos
    vista.getCbMarca().addActionListener(e -> {
        String nombreMarca = (String) vista.getCbMarca().getSelectedItem();
        actualizarModelosDinamico(nombreMarca);
    });

    // 3. GENERACIÓN AUTOMÁTICA DE AÑOS (Desde 1990 hasta el actual + 1)
    vista.getCbAnio().removeAllItems();
    int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    for (int i = anioActual + 1; i >= 1990; i--) {
        vista.getCbAnio().addItem(String.valueOf(i));
    }
}

private void actualizarModelosDinamico(String nombreMarca) {
    vista.getCbModelo().removeAllItems();
    if (nombreMarca == null) return;

    CatalogosDAO catDAO = new CatalogosDAO();
    // Buscamos el ID de la marca para traer sus modelos
    int idMarca = 0;
    for (Marca m : catDAO.listarMarcas()) {
        if (m.getNombre().equals(nombreMarca)) {
            idMarca = m.getIdMarca();
            break;
        }
    }

    for (Modelo mod : catDAO.listarModelos(idMarca)) {
        vista.getCbModelo().addItem(mod.getNombre());
    }
}

    // --- LÓGICA DE BASE DE DATOS ---

    private void registrarVehiculo() {
        try {
            String placa = vista.getTxtPlaca().getText().trim();
            int idCliente = Integer.parseInt(vista.getTxtIdCliente().getText().trim());
            
            // Validamos que se haya seleccionado algo real
            if (vista.getCbMarca().getSelectedItem() == null || vista.getCbMarca().getSelectedItem().equals("Seleccione...") ||
                vista.getCbModelo().getSelectedItem() == null || vista.getCbModelo().getSelectedItem().equals("Seleccione...") ||
                vista.getCbAnio().getSelectedItem() == null) {
                
                JOptionPane.showMessageDialog(vista, "Por favor seleccione Marca, Modelo y Año válidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String marca = vista.getCbMarca().getSelectedItem().toString();
            String modelo = vista.getCbModelo().getSelectedItem().toString();
            int anio = Integer.parseInt(vista.getCbAnio().getSelectedItem().toString());

            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "La placa es obligatoria.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Vehiculo vehiculo = new Vehiculo(placa, idCliente, marca, modelo, anio);

            if (dao.registrarVehiculo(vehiculo)) {
                JOptionPane.showMessageDialog(vista, "Vehículo registrado exitosamente.");
                limpiarCampos();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar (Verifique que el ID Cliente exista).", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El ID del cliente debe ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        vista.getModeloTabla().setRowCount(0);
        List<Vehiculo> lista = dao.listarVehiculos();
        
        for (Vehiculo v : lista) {
            Object[] fila = {
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAnio(),
                v.getIdCliente()
            };
            vista.getModeloTabla().addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.getTxtPlaca().setText("");
        vista.getTxtIdCliente().setText("");
        vista.getCbMarca().setSelectedIndex(0); // Resetea el combo en cascada
    }
}