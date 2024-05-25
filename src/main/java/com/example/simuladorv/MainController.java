package com.example.simuladorv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

public class MainController {

    // Variable para almacenar el estado del juego
    private EstadoJuego estadoJuego;

    // Botones de guardar y cargar partida

    private GameBoardController gameBoardController; // Declarar una referencia a GameBoardController

    // Método para establecer una referencia a GameBoardController
    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    private void handleLoadGame() {
        try {
            EstadoJuego estadoJuego = GameData.loadGame("EstadoJuego.ser");
            if (estadoJuego != null) {
                // Actualiza el juego con el estado cargado
                updateGameWithLoadedState(estadoJuego);
            } else {
                System.out.println("El archivo EstadoJuego.ser está vacío o no contiene datos válidos.");
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Método para actualizar el juego con el estado cargado
    private void updateGameWithLoadedState(EstadoJuego estadoJuego) {
        if (gameBoardController != null) { // Verifica si se ha establecido una referencia a GameBoardController
            GridPane gameGridPane = gameBoardController.getGameGridPane(); // Obtiene el GridPane de GameBoardController
            if (gameGridPane != null) { // Verifica si se obtuvo el GridPane correctamente
                // Obtener la disposición del tablero del estado cargado
                String boardLayoutInfo = estadoJuego.getBoardLayout();
                // Suponiendo que el formato es "Filas: X, Columnas: Y"
                String[] layoutParts = boardLayoutInfo.split(", ");
                int rows = Integer.parseInt(layoutParts[0].substring(layoutParts[0].indexOf(":") + 2));
                int cols = Integer.parseInt(layoutParts[1].substring(layoutParts[1].indexOf(":") + 2));

                // Limpiar el tablero existente
                gameGridPane.getChildren().clear();

                // Crear y mostrar las celdas del tablero basadas en el estado cargado
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        Label cellLabel = new Label("");
                        cellLabel.setMinSize(60, 60);
                        cellLabel.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                        gameGridPane.add(cellLabel, j, i);
                    }
                }
            } else {
                System.out.println("No se pudo obtener el GridPane de GameBoardController.");
            }
        } else {
            System.out.println("No se estableció una referencia a GameBoardController.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void openConfigScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigScreen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Configuración");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openGameBoard() throws IOException {
        int rows = Configuration.getRows();
        int cols = Configuration.getCols();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
        Parent root = loader.load();
        GameBoardController gameBoardController = loader.getController();
        gameBoardController.initializeGameBoard(rows, cols);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

