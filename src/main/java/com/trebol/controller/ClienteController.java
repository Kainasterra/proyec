package com.trebol.controller;

import com.trebol.dao.ClienteDAO;
import com.trebol.model.Cliente;
import com.trebol.view.PanelClientes;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteController {
    
    private PanelClientes vista;
    private ClienteDAO dao;

    public ClienteController(PanelClientes vista, ClienteDAO dao) {
        this.vista = vista;
        this.dao = dao;
        
        // Cargar los clientes en la tabla apenas se abre la pantalla
        cargarTabla();

        // Escuchar el clic del botón Guardar
        this.vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });
    }

    private void registrarCliente() {
        // 1. Obtener datos de las cajas de texto
        String nombre = vista.getTxtNombre().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();
        String direccion = vista.getTxtDireccion().getText().trim();

        // 2. Validar campos obligatorios
        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre y el teléfono son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Crear el modelo Cliente
        Cliente cliente = new Cliente(0, nombre, telefono, correo, direccion);

        // 4. Guardar usando el DAO
        if (dao.registrarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla(); // Actualizar la tabla visualmente
        } else {
            JOptionPane.showMessageDialog(vista, "Error al guardar el cliente en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        // Limpiar la tabla
        vista.getModeloTabla().setRowCount(0);
        
        // Pedir la lista de clientes al DAO
        List<Cliente> lista = dao.listarClientes();
        
        // Llenar la tabla fila por fila
        for (Cliente c : lista) {
            Object[] fila = {
                c.getIdCliente(),
                c.getNombre(),
                c.getTelefono(),
                c.getCorreo(),
                c.getDireccion()
            };
            vista.getModeloTabla().addRow(fila);
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombre().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtDireccion().setText("");
    }
}