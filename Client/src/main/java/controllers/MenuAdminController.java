package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.WindowChanger;

public class MenuAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button dbWorkButton;

    @FXML
    private Button showGrButton;

    @FXML
    private Button showTeachButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button infStudButton;

    @FXML
    private Button profitButton;

    @FXML
    private Button averagePerfButton;

    @FXML
    void averagePerf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("getChartProgress");
        WindowChanger.changeWindow(getClass(), infStudButton, "adminDiagrProgress.fxml", "", false);
    }

    @FXML
    void infStud(ActionEvent event) throws IOException {
        Connect.client.sendMessage("studInf");
        WindowChanger.changeWindow(getClass(), infStudButton, "studentInf.fxml", "", false);
    }

    @FXML
    void profit(ActionEvent event) throws IOException {
        Connect.client.sendMessage("getDiagrReceive");
        WindowChanger.changeWindow(getClass(), infStudButton, "adminDiagrReceive.fxml", "", false);
    }

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("adminInf");
        WindowChanger.changeWindow(getClass(), personalInfButton, "adminInformation.fxml", "", false);
    }

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }

    @FXML
    void dbWork(ActionEvent event) {
        dbWorkButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dbWork.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }

    @FXML
    void showGrTimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showGrTimetable");
        WindowChanger.changeWindow(getClass(), showGrButton, "groupsTimetable.fxml", "", false);
    }

    @FXML
    void showTTimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showGrTeacher");
        WindowChanger.changeWindow(getClass(), showTeachButton, "showTeachers.fxml", "", false);
    }

    @FXML
    void initialize() {

    }
}
