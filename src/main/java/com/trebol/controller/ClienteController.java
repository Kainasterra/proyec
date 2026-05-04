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
    private int idClienteSeleccionado = -1;

    public ClienteController(PanelClientes vista, ClienteDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // Listeners de botones
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);

        // Listener para la tabla
        this.vista.getTablaClientes().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        // --- NUEVO: Listener para el Buscador (Tiempo Real) ---
        this.vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrar();
            }
        });

        listar();
    }

    // Método que realiza la magia del filtrado
    private void filtrar() {
        String texto = vista.getTxtBuscar().getText();
        // (?i) hace que sea insensible a mayúsculas/minúsculas
        vista.getSorter().setRowFilter(RowFilter.regexFilter("(?i)" + texto));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) registrar();
        if (e.getSource() == vista.getBtnActualizar()) actualizar();
        if (e.getSource() == vista.getBtnEliminar()) eliminar();
    }

    private void registrar() {
        Cliente c = new Cliente(
            vista.getTxtNombre().getText(),
            vista.getTxtTelefono().getText(),
            vista.getTxtCorreo().getText()
        );
        if (dao.registrarCliente(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado.");
            limpiarYCargar();
        }
    }

    private void actualizar() {
        if (idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un cliente de la tabla.");
            return;
        }
        Cliente c = new Cliente(
            idClienteSeleccionado,
            vista.getTxtNombre().getText(),
            vista.getTxtTelefono().getText(),
            vista.getTxtCorreo().getText()
        );
        if (dao.actualizarCliente(c)) {
            JOptionPane.showMessageDialog(vista, "Cliente actualizado.");
            limpiarYCargar();
        }
    }

    private void eliminar() {
        if (idClienteSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un cliente.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Eliminar cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminarCliente(idClienteSeleccionado)) {
                limpiarYCargar();
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTablaClientes().getSelectedRow();
        if (fila != -1) {
            // Importante: convertir el índice de la vista al modelo por si hay filtro activo
            int filaModelo = vista.getTablaClientes().convertRowIndexToModel(fila);
            
            idClienteSeleccionado = (int) vista.getModeloTabla().getValueAt(filaModelo, 0);
            vista.getTxtNombre().setText(vista.getModeloTabla().getValueAt(filaModelo, 1).toString());
            vista.getTxtTelefono().setText(vista.getModeloTabla().getValueAt(filaModelo, 2).toString());
            vista.getTxtCorreo().setText(vista.getModeloTabla().getValueAt(filaModelo, 3).toString());
        }
    }

    private void listar() {
        vista.getModeloTabla().setRowCount(0);
        List<Cliente> lista = dao.listarClientes();
        for (Cliente c : lista) {
            vista.getModeloTabla().addRow(new Object[]{
                c.getIdCliente(), c.getNombre(), c.getTelefono(), c.getCorreo()
            });
        }
    }

    private void limpiarYCargar() {
        vista.getTxtNombre().setText("");
        vista.getTxtTelefono().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtBuscar().setText(""); // Limpiar buscador
        vista.getSorter().setRowFilter(null); // Quitar filtro
        idClienteSeleccionado = -1;
        listar();
    }
}