package com.mensageria.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.mensageria.dao.AlunoDAO;
import com.mensageria.model.Alunos;
import com.mensageria.model.Curso;

public class AlunoView extends Application {

    private TableView<Alunos> tableAlunos;
    private TextField nomeInput, telefoneInput, sexoInput;
    private ComboBox<Curso> cursoInput;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestão de Alunos e Cursos");

        VBox layout = new VBox(10);

        tableAlunos = new TableView<>();
        TableColumn<Alunos, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(data -> data.getValue().nomeProperty());

        TableColumn<Alunos, String> telefoneColuna = new TableColumn<>("Telefone");
        telefoneColuna.setCellValueFactory(data -> data.getValue().telefoneProperty());

        TableColumn<Alunos, String> cursoColuna = new TableColumn<>("Curso");
        cursoColuna.setCellValueFactory(data -> data.getValue().getCurso().siglaProperty());

        tableAlunos.getColumns().addAll(nomeColuna, telefoneColuna, cursoColuna);

        nomeInput = new TextField();
        nomeInput.setPromptText("Nome");

        telefoneInput = new TextField();
        telefoneInput.setPromptText("Telefone");

        sexoInput = new TextField();
        sexoInput.setPromptText("Sexo");

        cursoInput = new ComboBox<>();

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> adicionarAluno());

        Button updateButton = new Button("Atualizar");
        updateButton.setOnAction(e -> atualizarAluno());

        Button deleteButton = new Button("Deletar");
        deleteButton.setOnAction(e -> deletarAluno());

        HBox formulario = new HBox(10);
        formulario.getChildren().addAll(nomeInput, telefoneInput, sexoInput, cursoInput, addButton, updateButton, deleteButton);

        layout.getChildren().addAll(tableAlunos, formulario);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void adicionarAluno() {
        Alunos aluno = new Alunos();
        aluno.setNome(nomeInput.getText());
        aluno.setTelefone(telefoneInput.getText());
        aluno.setSexo(sexoInput.getText());
        aluno.setCurso(cursoInput.getValue());

        new AlunoDAO().create(aluno);

        tableAlunos.getItems().add(aluno);
    }

    private void atualizarAluno() {
        Alunos alunoSelecionado = tableAlunos.getSelectionModel().getSelectedItem();
        alunoSelecionado.setNome(nomeInput.getText());
        alunoSelecionado.setTelefone(telefoneInput.getText());
        alunoSelecionado.setSexo(sexoInput.getText());
        alunoSelecionado.setCurso(cursoInput.getValue());

        new AlunoDAO().update(alunoSelecionado);

        tableAlunos.refresh();
    }

    private void deletarAluno() {
        Alunos alunoSelecionado = tableAlunos.getSelectionModel().getSelectedItem();
        new AlunoDAO().delete(alunoSelecionado);

        tableAlunos.getItems().remove(alunoSelecionado);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
