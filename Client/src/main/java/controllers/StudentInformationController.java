package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Students;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.WindowChanger;

public class StudentInformationController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TableView<Students> studTable;

    @FXML
    private TableColumn<Students, String> loginColumn;

    @FXML
    private TableColumn<Students, String> firstnameColumn;

    @FXML
    private TableColumn<Students, String> lastnameColumn;

    @FXML
    private TableColumn<Students, String> averageMarkColumn;

    @FXML
    private TableColumn<Students, Integer> groupColumn;

    @FXML
    private TableColumn<Students, Integer> paymentColumn;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuStudent.fxml", "", false);
    }

    @FXML
    void changestud(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firstnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        averageMarkColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getAverageMark()));
        groupColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getGroup()));
        paymentColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPayment()));
        studTable.setItems(getStudent());
    }

    private ObservableList<Students> getStudent() {
        ObservableList<Students> studList = FXCollections.observableArrayList();
        Students students = (Students) Connect.client.readObject();
        System.out.println(students);
        studList.add(students);
        studTable.setItems(studList);
        return studList;
    }
}
