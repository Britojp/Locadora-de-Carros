package telas;

import arqs.SalvarArquivos;
import models.Funcionario;
import models.Gerente;
import models.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaMostrarFuncionarios {
    private ArrayList<Funcionario> listaFuncionarios;
    private boolean isEditingEnabled = false;
    private SalvarArquivos salvarArquivos;

    public TelaMostrarFuncionarios(ArrayList<Funcionario> listaFuncionarios, SalvarArquivos salvarArquivos) {
        this.listaFuncionarios = listaFuncionarios;
        this.salvarArquivos = salvarArquivos;
    }

    public void mostrarTodosFuncionarios() {
        JFrame frame = new JFrame("Lista de Funcionários");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Id", "Nome", "CPF", "Idade", "Cargo", "Salário", "Meta do vendedor", "Porcentagem Comissão (%)", "Total comissão a receber", "Login", "Senha"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Salvar");
        JButton editButton = new JButton("Editar");
        JButton updateButton = new JButton("Atualizar");
        JButton returnButton = new JButton("Voltar");

        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Preenche a tabela com os dados iniciais
        atualizarTabela(model);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditingEnabled) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        Funcionario funcionario = listaFuncionarios.get(i);
                        String novoCargo = (String) model.getValueAt(i, 4);

                        try {
                            if (funcionario instanceof Gerente && novoCargo.equals("Vendedor")) {
                                JOptionPane.showMessageDialog(frame, "Não é permitido alterar um Gerente para Vendedor.", "Erro", JOptionPane.ERROR_MESSAGE);
                                continue;
                            } else if (funcionario instanceof Vendedor && novoCargo.equals("Gerente")) {
                                Gerente novoGerente = new Gerente(
                                        (String) model.getValueAt(i, 1),
                                        (String) model.getValueAt(i, 2),
                                        parseShort(model.getValueAt(i, 3)),
                                        parseDouble(model.getValueAt(i, 5)),
                                        funcionario.getCredenciais()
                                );
                                novoGerente.setValorMeta(parseDouble(model.getValueAt(i, 6)));
                                novoGerente.setPorcentagemComissao(parseDouble(model.getValueAt(i,7)));
                                novoGerente.setComissaoTotal(parseDouble(model.getValueAt(i, 8)));
                                listaFuncionarios.set(i, novoGerente);

                            } else {
                                funcionario.setNome((String) model.getValueAt(i, 1));
                                funcionario.setCPF((String) model.getValueAt(i, 2));
                                funcionario.setIdade(parseShort(model.getValueAt(i, 3)));
                                funcionario.setSalario(parseDouble(model.getValueAt(i, 5)));
                                funcionario.setValorMeta(parseDouble(model.getValueAt(i, 6)));
                                funcionario.setComissaoTotal(parseDouble(model.getValueAt(i, 8)));

                                if (funcionario instanceof Gerente) {
                                    funcionario.setValorMeta(0);
                                    funcionario.setPorcentagemComissao(0);
                                    funcionario.setComissaoTotal(0);
                                }

                                funcionario.getCredenciais().setNomeUsuario((String) model.getValueAt(i, 9));
                                funcionario.getCredenciais().setSenha((String) model.getValueAt(i, 10));
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Formato de número inválido na linha " + (i + 1) + ". " + ex.getMessage(), "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    salvarArquivos.salvarFuncionarios(listaFuncionarios);
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

            private double parseDouble(Object value) throws NumberFormatException {
                if (value instanceof Number) {
                    return ((Number) value).doubleValue();
                }
                return Double.parseDouble(value.toString());
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEditingEnabled = !isEditingEnabled;
                if (isEditingEnabled) {
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));  // Permite edição
                    table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JComboBox<>(new String[]{"Gerente", "Vendedor"})));  // Adiciona o JComboBox para a coluna de cargo
                    editButton.setText("Desativar Edição");
                    JOptionPane.showMessageDialog(null, "Agora você pode editar as células.");
                } else {
                    table.setDefaultEditor(Object.class, null);
                    editButton.setText("Editar");
                    JOptionPane.showMessageDialog(null, "Edição desativada.");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela(model);
                JOptionPane.showMessageDialog(null, "Tabela atualizada com sucesso!");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    private void atualizarTabela(DefaultTableModel model) {
        model.setRowCount(0);

        for (Funcionario funcionario : listaFuncionarios) {
            Object[] row;
            if (funcionario instanceof Gerente gerente) {
                row = new Object[]{
                        gerente.getIdFuncionario(),
                        gerente.getNome(),
                        gerente.getCPF(),
                        gerente.getIdade(),
                        gerente.getCargo(),
                        gerente.getSalario(),
                        gerente.getValorMeta(),
                        gerente.getPorcentagemComissao(),
                        gerente.getComissaoTotal(),
                        gerente.getCredenciais().getNomeUsuario(),
                        gerente.getCredenciais().getSenha()
                };
            } else {
                Vendedor vendedor = (Vendedor) funcionario;
                row = new Object[]{
                        vendedor.getIdFuncionario(),
                        vendedor.getNome(),
                        vendedor.getCPF(),
                        vendedor.getIdade(),
                        vendedor.getCargo(),
                        vendedor.getSalario(),
                        vendedor.getValorMeta(),
                        vendedor.getPorcentagemComissao(),
                        vendedor.getComissaoTotal(),
                        vendedor.getCredenciais().getNomeUsuario(),
                        vendedor.getCredenciais().getSenha()
                };
            }
            model.addRow(row);
        }
    }
}
