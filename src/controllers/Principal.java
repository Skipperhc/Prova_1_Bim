package controllers;

import java.util.List;

import dao.EventoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Evento;

/**
 * @author Vitor Hainosz
 *
 */
public class Principal extends Application{
	
	private static Stage principalStage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../views/TelaPrincipal.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Prova 1 Bimestre");
			primaryStage.show();
			principalStage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return principalStage;
	}
}
