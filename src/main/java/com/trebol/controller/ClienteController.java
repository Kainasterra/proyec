package com.trebol.controller;

import com.trebol.dao.ClienteDAO;
import com.trebol.model.Cliente;
import com.trebol.view.PanelClientes;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ClienteController implements ActionListener {
    private PanelClientes vista;
    private ClienteDAO dao;
    private int idClienteSeleccionado = -1; // Almacena el ID del cliente que estamos editando

    public ClienteController(PanelClientes vista, ClienteDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // 1. Escuchar los clics de los botones
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);

        // 2. Escuchar clics en la tabla para cargar datos
        this.vista.getTablaClientes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        // 3. Mostrar los datos iniciales
        listarClientes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) registrar();
        if (e.getSource() == vista.getBtnActualizar()) actualizar();
        if (e.getSource() == vista.getBtnEliminar()) eliminar();
    }

    private void registrar() {
        if (validarCampos()) {
            Cliente c = extraerDatos();
            if (dao.registrarCliente(c)) {
                JOptionPane.showMessageDialog(vista, "¡Cliente guardado con éxito!");
                limpiarYCargar();
            }
        }
    }

    private void actualizar() {
    if (idClienteSeleccionado == -1) {
        JOptionPane.showMessageDialog(vista, "Selecciona un cliente de la tabla.");
        return;
    }
    
    // CREAMOS EL OBJETO
    Cliente c = extraerDatos();
    // ¡ESTA LÍNEA ES VITAL! Sin ella, el ID que se envía es 0 o nulo
    c.setIdCliente(idClienteSeleccionado); 
    
    if (dao.actualizarCliente(c)) {
        JOptionPane.showMessageDialog(vista, "¡Cliente actualizado!");
        limpiarYCargar();
    }
}
    private void eliminar() {
        if (idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un cliente para eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarCliente(idClienteSeleccionado)) {
                limpiarYCargar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se puede eliminar (revisa si tiene vehículos asociados).");
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTablaClientes().getSelectedRow();
        if (fila != -1) {
            idClienteSeleccionado = (int) vista.getModeloTabla().getValueAt(fila, 0);
            vista.getTxtNombre().setText(vista.getModeloTabla().getValueAt(fila, 1).toString());
            vista.getTxtTelefono().setText(vista.getModeloTabla().getValueAt(fila, 2).toString());
            vista.getTxtCorreo().setText(vista.getModeloTabla().getValueAt(fila, 3).toString());
        }
    }

    private void listarClientes() {
        vista.getModeloTabla().setRowCount(0);
        List<Cliente> lista = dao.listarClientes();
        for (Cliente c : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                c.getIdCliente(), c.getNombre(), c.getTelefono(), c.getCorreo(), c.getDireccion()
            });
        }
    }

    private Cliente extraerDatos() {
        return new Cliente(
            vista.getTxtNombre().getText(),
            vista.getTxtTelefono().getText(),
            vista.getTxtCorreo().getText(),
                ""
        );
    }

    private boolean validarCampos() {
        if (vista.getTxtNombre().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El nombre es obligatorio.");
            return false;
        }
        return true;
    }

    private void limpiarYCargar() {
        vista.getTxtNombre().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtCorreo().setText("");
        idClienteSeleccionado = -1;
        vista.getTablaClientes().clearSelection();
        listarClientes();
    }
}