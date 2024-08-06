package telas;

import arqs.SalvarArquivos;
import models.Carros;
import models.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaMostrarClientes {
    private ArrayList<Cliente> listaClientes;
    private SalvarArquivos salvarArquivos;
    private boolean isEditingEnabled = false;  // Flag para controle de edição

    public TelaMostrarClientes(ArrayList<Cliente> listaClientes, SalvarArquivos salvarArquivos) {
        this.listaClientes = listaClientes;
        this.salvarArquivos = salvarArquivos;
    }

    public void mostrarTodosClientes() {
        JFrame frame = new JFrame("Lista de Clientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Id", "Nome", "CPF", "Idade", "Carros Alugados", "Número de Habilitação"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Cliente cliente : listaClientes) {
            Object[] row = new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNome(),
                    cliente.getCPF(),
                    cliente.getIdade(),
                    cliente.getCarrosAlugados().size(),
                    cliente.getNumHabilitacao()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);
        table.setDefaultEditor(Object.class, null);  // Desativa a edição inicialmente
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Salvar");
        JButton editButton = new JButton("Editar");
        JButton returnButton = new JButton("Voltar");

        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(returnButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditingEnabled) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        Cliente cliente = listaClientes.get(i);
                        try {
                            cliente.setNome((String) model.getValueAt(i, 1));
                            cliente.setCPF((String) model.getValueAt(i, 2));
                            cliente.setIdade(parseShort(model.getValueAt(i, 3)));
                            cliente.setNumHabilitacao((String) model.getValueAt(i, 5));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Formato de número inválido na linha " + (i + 1) + ". " + ex.getMessage(), "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    salvarArquivos.salvarClientes(listaClientes);
                    JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");

                } else {
                    JOptionPane.showMessageDialog(null, "Você deve habilitar a edição antes de salvar.");
                }
            }

            private short parseShort(Object value) throws NumberFormatException {
                if (value instanceof Number) {
                    return ((Number) value).shortValue();
                }
                return Short.parseShort(value.toString());
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEditingEnabled = !isEditingEnabled;
                if (isEditingEnabled) {
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));  // Permite edição
                    editButton.setText("Desativar Edição");
                    JOptionPane.showMessageDialog(null, "Agora você pode editar as células.");
                } else {
                    table.setDefaultEditor(Object.class, null);  // Desativa edição
                    editButton.setText("Editar");
                    JOptionPane.showMessageDialog(null, "Edição desativada.");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
}
