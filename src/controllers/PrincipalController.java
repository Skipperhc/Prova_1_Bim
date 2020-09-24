package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import dao.EventoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputMethodEvent;
import models.Evento;
import utils.exceptions.CamposNulosException;

public class PrincipalController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextArea txtArea;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtValor;

	@FXML
	private DatePicker dataPicker;

	@FXML
	private Button btnAdd;

	@FXML
	private Button BtnEdit;

	@FXML
	private TextField txtCod;

	@FXML
	private Button btnDel;

	@FXML
	private Button BtnPesquisar;

	@FXML
	void adicionar(ActionEvent event) {
		try {
			if (txtNome.getText().isEmpty())
				throw new CamposNulosException("Campo nome vazio!!");
			if (txtValor.getText().isEmpty())
				throw new CamposNulosException("Campo valor vazio!!");
			if (dataPicker.getValue() == null)
				throw new CamposNulosException("Campo data vazio!!");

			EventoDAO dao = new EventoDAO();
			Calendar cal = Calendar.getInstance();
			LocalDate dataP = dataPicker.getValue();
			cal.set(dataP.getYear(), dataP.getMonthValue(), dataP.getDayOfMonth());
			dao.inserir(new Evento(txtNome.getText(), cal, Float.parseFloat(txtValor.getText())));
			zerarCampos();
			atualizar();
		} catch (CamposNulosException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Preencha todos os campos");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Insira um número no campo valor");
			alert.setContentText("");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROOOO");
			alert.setHeaderText("Erro");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	void atualizar() {
		EventoDAO dao = new EventoDAO();
		List<Evento> lista = dao.listar();
		txtArea.setText("");
		if(lista.size() == 0) {
			txtArea.setText("No momento tem não tem eventos cadastrados\n\n");
		} else if(lista.size() == 1) {
			txtArea.setText("No momento tem apenas 1 evento cadastrado\n\n");
		} else {
			txtArea.setText("No momento tem " + lista.size() + " eventos cadastrados\n\n");
		}
		for (int i = 0; i < lista.size(); i++) {
			txtArea.setText(txtArea.getText() + "ID - " + lista.get(i).getCod() + ": " + lista.get(i) + "\n");
		}
	}

	@FXML
	void deletar(ActionEvent event) {
		try {
			EventoDAO dao = new EventoDAO();
			dao.deletar(Integer.parseInt(txtCod.getText()));
			zerarCampos();
			atualizar();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Insira um número no campo Id");
			alert.setContentText("");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROOOO");
			alert.setHeaderText("Erro");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	@FXML
	void editar(ActionEvent event) {
		try {
			if (txtNome.getText().isEmpty())
				throw new CamposNulosException("Campo nome vazio!!");
			if (txtValor.getText().isEmpty())
				throw new CamposNulosException("Campo valor vazio!!");
			if (dataPicker.getValue() == null)
				throw new CamposNulosException("Campo data vazio!!");

			EventoDAO dao = new EventoDAO();
			Calendar cal = Calendar.getInstance();
			LocalDate dataP = dataPicker.getValue();
			cal.set(dataP.getYear(), dataP.getMonthValue(), dataP.getDayOfMonth());
			dao.editar(new Evento(Integer.parseInt(txtCod.getText()), txtNome.getText(), cal,
					Float.parseFloat(txtValor.getText())));
			zerarCampos();
			atualizar();
		} catch (CamposNulosException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Preencha todos os campos");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Insira um número no campo valor e Id");
			alert.setContentText("");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROOOO");
			alert.setHeaderText("Erro");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	@FXML
	void pesquisar(ActionEvent event) {
		try {
			if (txtCod.getText().isEmpty()) {
				atualizar();
			} else {
				EventoDAO dao = new EventoDAO();
				dao.buscarId(Integer.parseInt(txtCod.getText()));
				List<Evento> lista = dao.buscarId(Integer.parseInt(txtCod.getText()));
				txtArea.setText("");
				for (int i = 0; i < lista.size(); i++) {
					txtArea.setText(txtArea.getText() + "ID - " + lista.get(i).getCod() + ": " + lista.get(i) + "\n");
				}
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aviso");
			alert.setHeaderText("Insira um número no campo Id");
			alert.setContentText("");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROOOO");
			alert.setHeaderText("Erro");
			alert.setContentText("");
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() {
		assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Untitled'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Untitled'.";
		assert txtValor != null : "fx:id=\"txtValor\" was not injected: check your FXML file 'Untitled'.";
		assert dataPicker != null : "fx:id=\"dataPicker\" was not injected: check your FXML file 'Untitled'.";
		assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Untitled'.";
		assert BtnEdit != null : "fx:id=\"BtnEdit\" was not injected: check your FXML file 'Untitled'.";
		assert txtCod != null : "fx:id=\"txtCod\" was not injected: check your FXML file 'Untitled'.";
		assert btnDel != null : "fx:id=\"btnDel\" was not injected: check your FXML file 'Untitled'.";
		assert BtnPesquisar != null : "fx:id=\"BtnPesquisar\" was not injected: check your FXML file 'Untitled'.";

	}

	private void zerarCampos() {
		txtCod.setText("");
		txtNome.setText("");
		txtValor.setText("");
		LocalDate lb = LocalDate.of(2020, 9, 24);
		dataPicker.setValue(lb);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Me desculpa ai Martin");
//		alert.setHeaderText("Então é o seguinte:");
//		alert.setContentText("Reparei agora que ficou meio confuso o código então algumas explicações");
//		alert.showAndWait();
//		alert.setHeaderText("Pesquisar:");
//		alert.setContentText("Para pesquisar terás de colocar o Id e depois clicar em pesquisar \nPara mostrar tudo deixe o campo id sem nada \nClique em pesquisar");
//		alert.showAndWait();
//		alert.setHeaderText("Adicionar:");
//		alert.setContentText("Para adicionar terás de preencher os campos e depois clicar em adicionar");
//		alert.showAndWait();
//		alert.setHeaderText("Deletar:");
//		alert.setContentText("Para deletar terás de colocar o Id e depois clicar em deletar");
//		alert.showAndWait();
//		alert.setHeaderText("Editar:");
//		alert.setContentText("Para editar terás de colocar o Id que quer editar \npreencher os campos com os novos dados \ndepois clicar em editar");
//		alert.showAndWait();
//		alert.setHeight(500);
//		alert.setWidth(400);
//		alert.setHeaderText("E por ultimo:");
//		alert.setContentText("Sei que deve ser um ultraje falar isso\nMas para se conectar no banco\n"
//				+ "Criar o um acesso como DBA \nuser: Usuario - senha: 123123@senha\n"
//				+ "ou - Alterar a classe ConexaoMySql para seu usuario e senha\n"
//				+ "Deixei dentro da pasta script os comandos para criar o banco\n"
//				+ "Não consegui fazer o java criar o banco sozinho, bimestre que vem vai");
//		alert.showAndWait();
		zerarCampos();
		atualizar();
	}
}
