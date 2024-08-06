package telas;

import arqs.SalvarArquivos;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaMostrarCarros {
    private ArrayList<Carros> listaCarros;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Funcionario> listaFuncionarios;
    private boolean isEditingEnabled = false;
    private SalvarArquivos salvarArquivos;

    public TelaMostrarCarros(SalvarArquivos salvarArquivos,ArrayList<Carros> listaCarros, ArrayList<Cliente> listaClientes, ArrayList<Funcionario> listaFuncionarios) {
        this.listaCarros = listaCarros;
        this.listaClientes = listaClientes;
        this.listaFuncionarios = listaFuncionarios;
        this.salvarArquivos = salvarArquivos;
    }

    public void mostrarTodosCarros() {
        JFrame frame = new JFrame("Lista de Carros");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 500);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Id", "Nome", "Cor", "Placa", "Valor carro", "Alugado", "Valor aluguel", "Tempo aluguel (dias)", "Cliente", "Vendedor", "Valor aluguel total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (listaCarros == null || listaCarros.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum carro disponível para exibição.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Carros carros : listaCarros) {
                Object[] row = {
                        carros.getIdCarro(),
                        carros.getNomeCarro(),
                        carros.getCorCarro(),
                        carros.getPlacaCarro(),
                        carros.getValorCarro(),
                        carros.getAluguel().isAlugado(),
                        carros.getAluguel().getValorAluguel(),
                        carros.getAluguel().getTempoAluguel(),
                        carros.getAluguel().getCliente() != null ? carros.getAluguel().getCliente().getNome() : null,
                        carros.getAluguel().getFuncionario() != null ? carros.getAluguel().getFuncionario().getNome() : null,
                        carros.getAluguel().getValorTotal(),
                };
                model.addRow(row);
            }
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);

        JComboBox<Boolean> alugadoComboBox = new JComboBox<>(new Boolean[]{true, false});
        table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(alugadoComboBox));

        JComboBox<String> clienteComboBox = new JComboBox<>();
        for (Cliente cliente : listaClientes) {
            clienteComboBox.addItem(cliente.getNome());
        }
        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(clienteComboBox));

        JComboBox<String> vendedorComboBox = new JComboBox<>();
        for (Funcionario funcionario : listaFuncionarios) {
            vendedorComboBox.addItem(funcionario.getNome());
        }
        table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(vendedorComboBox));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton returnButton = new JButton("Voltar");
        JButton saveButton = new JButton("Salvar");
        JButton editButton = new JButton("Editar");
        JButton refreshButton = new JButton("Atualizar");

        buttonPanel.add(returnButton);
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(refreshButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
                frame.revalidate();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditingEnabled) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        Carros carros = listaCarros.get(i);
                        try {
                            carros.setNomeCarro((String) model.getValueAt(i, 1));
                            carros.setCorCarro((String) model.getValueAt(i, 2));
                            carros.setPlacaCarro((String) model.getValueAt(i, 3));

                            Object valorObj = model.getValueAt(i, 4);
                            double valorCarro;
                            if (valorObj instanceof Number) {
                                valorCarro = ((Number) valorObj).doubleValue();
                            } else {
                                valorCarro = Double.parseDouble(valorObj.toString());
                            }
                            carros.setValorCarro(valorCarro);

                            boolean isAlugado = (Boolean) model.getValueAt(i, 5);
                            carros.getAluguel().setAlugado(isAlugado);

                            if (isAlugado) {
                                Object valorAluguelObj = model.getValueAt(i, 6);
                                double valorAluguel;
                                if (valorAluguelObj instanceof Number) {
                                    valorAluguel = ((Number) valorAluguelObj).doubleValue();
                                } else {
                                    valorAluguel = Double.parseDouble(valorAluguelObj.toString());
                                }
                                carros.getAluguel().setValorAluguel(valorAluguel);

                                Object tempoObj = model.getValueAt(i, 7);
                                int tempoAluguel = Integer.parseInt(tempoObj.toString());
                                carros.getAluguel().setTempoAluguel(tempoAluguel);

                                String clienteNomeStr = (String) model.getValueAt(i, 8);
                                Cliente cliente = findClienteByName(clienteNomeStr);

                                String vendedorNomeStr = (String) model.getValueAt(i, 9);
                                Funcionario vendedor = findFuncionarioByName(vendedorNomeStr);

                                if (cliente == null || vendedor == null) {
                                    JOptionPane.showMessageDialog(frame, "Alugado requer Cliente e Vendedor associados.", "Erro", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    carros.getAluguel().setCliente(cliente);
                                    carros.getAluguel().setFuncionario(vendedor);

                                    for(Cliente c1 : listaClientes) {
                                        if(c1.getCarrosAlugados()!=carros) {
                                            cliente.addCarro(carros);
                                        }
                                    }
                                    vendedor.addCarro(carros);
                                    JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");
                                    salvarArquivos.salvarCarros(listaCarros);
                                }
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Formato de número inválido na linha " + (i + 1) + ". " + ex.getMessage(), "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Você deve habilitar a edição antes de salvar.");
                }
            }

            private Cliente findClienteByName(String nome) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.getNome().equals(nome)) {
                        return cliente;
                    }
                }
                return null;
            }

            private Funcionario findFuncionarioByName(String nome) {
                for (Funcionario funcionario : listaFuncionarios) {
                    if (funcionario.getNome().equals(nome)) {
                        return funcionario;
                    }
                }
                return null;
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEditingEnabled = !isEditingEnabled;
                if (isEditingEnabled) {
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
                    editButton.setText("Desativar Edição");
                    JOptionPane.showMessageDialog(null, "Agora você pode editar as células.");
                } else {
                    table.setDefaultEditor(Object.class, null);
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
