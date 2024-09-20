package com.mensageria.view;

import com.mensageria.dao.AlunoDAO;
import com.mensageria.model.Alunos;
import com.mensageria.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class AlunoView extends JFrame {

    private JTable tableAlunos;
    private JTextField nomeInput, telefoneInput, sexoInput;
    private JComboBox<Curso> cursoInput;
    private DefaultTableModel tableModel;

    public AlunoView() {
        setTitle("Gestão de Alunos e Cursos");
        setLayout(new BorderLayout());

        // Inicialização da tabela
        tableModel = new DefaultTableModel(new Object[]{"Matrícula", "Nome", "Telefone", "Curso", "Sexo"}, 0);
        tableAlunos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableAlunos);

        // Inicialização dos campos de entrada
        nomeInput = new JTextField(10);
        telefoneInput = new JTextField(10);
        sexoInput = new JTextField(10);
        cursoInput = new JComboBox<>(getCursos());  // Popula o comboBox com os cursos disponíveis

        // Botões
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> adicionarAluno());

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(e -> atualizarAluno());

        JButton deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(e -> deletarAluno());

        // Layout do formulário
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeInput);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(telefoneInput);
        formPanel.add(new JLabel("Sexo:"));
        formPanel.add(sexoInput);
        formPanel.add(new JLabel("Curso:"));
        formPanel.add(cursoInput);

        // Layout dos botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Adicionando componentes ao JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configuração da janela
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Método para adicionar um aluno
    private void adicionarAluno() {
        Alunos aluno = new Alunos();
        aluno.setNome(nomeInput.getText());
        aluno.setTelefone(telefoneInput.getText());
        aluno.setSexo(sexoInput.getText());
        aluno.setCurso((Curso) cursoInput.getSelectedItem());

        new AlunoDAO().create(aluno);

        tableModel.addRow(new Object[]{
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getTelefone(),
                aluno.getCurso() != null ? aluno.getCurso().getSigla() : "N/A",
                aluno.getSexo()
        });
    }

    // Método para atualizar os dados de um aluno selecionado
    private void atualizarAluno() {
        int selectedRow = tableAlunos.getSelectedRow();
        if (selectedRow >= 0) {
            Long matricula = (Long) tableModel.getValueAt(selectedRow, 0);
            Optional<Alunos> optionalAluno = new AlunoDAO().findById(matricula);
            optionalAluno.ifPresent(alunoSelecionado -> {
                alunoSelecionado.setNome(nomeInput.getText());
                alunoSelecionado.setTelefone(telefoneInput.getText());
                alunoSelecionado.setSexo(sexoInput.getText());
                alunoSelecionado.setCurso((Curso) cursoInput.getSelectedItem());

                new AlunoDAO().update(alunoSelecionado);

                // Atualizando os dados na tabela
                tableModel.setValueAt(alunoSelecionado.getNome(), selectedRow, 1);
                tableModel.setValueAt(alunoSelecionado.getTelefone(), selectedRow, 2);
                tableModel.setValueAt(alunoSelecionado.getCurso() != null ? alunoSelecionado.getCurso().getSigla() : "N/A", selectedRow, 3);
                tableModel.setValueAt(alunoSelecionado.getSexo(), selectedRow, 4);
            });
        }
    }

    // Método para deletar um aluno
    private void deletarAluno() {
        int selectedRow = tableAlunos.getSelectedRow();
        if (selectedRow >= 0) {
            Long matricula = (Long) tableModel.getValueAt(selectedRow, 0);
            Optional<Alunos> optionalAluno = new AlunoDAO().findById(matricula);
            optionalAluno.ifPresent(alunoSelecionado -> {
                new AlunoDAO().delete(alunoSelecionado);
                tableModel.removeRow(selectedRow);
            });
        }
    }

    // Método para obter a lista de cursos (exemplo básico)
    private Curso[] getCursos() {
        // Supondo que AlunoDAO tenha um método para buscar cursos
        return new Curso[] {
                new Curso(1L, "Engenharia", "ENG", null),
                new Curso(2L, "Medicina", "MED", null),
                new Curso(3L, "Direito", "DIR", null)
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AlunoView::new);
    }
}
