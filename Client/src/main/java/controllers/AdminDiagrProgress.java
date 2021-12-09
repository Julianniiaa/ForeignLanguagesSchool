package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ClientWork.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import util.Dialog;
import util.WindowChanger;

public class AdminDiagrProgress {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    private PieChart progressDiagr;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @FXML
    void save(ActionEvent event) {
        Connect.client.sendMessage("writeProgressReport");

        String mes = "";
        try {
            mes = Connect.client.readMessage();
        } catch(IOException ex){
            System.out.println("Ничего нет");
        }
        System.out.println(mes);
        if (mes.equals("Ошибка при составлении отчета"))
            Dialog.showAlertWithData();
        else {
            Dialog.correctOperation();
        }
    }

    @FXML
    void initialize() {
        ArrayList<AbstractMap.SimpleEntry<String, Double>> data
                = (ArrayList<AbstractMap.SimpleEntry<String, Double>>) Connect.client.readObject();

        for (AbstractMap.SimpleEntry<String, Double> point: data) {
            progressDiagr.getData().add(new PieChart.Data(point.getKey(), point.getValue()));
        }

        progressDiagr.setLegendSide(Side.LEFT);
    }
}
