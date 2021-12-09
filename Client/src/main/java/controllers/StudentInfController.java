package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.TextField;
import util.Dialog;
import util.WindowChanger;

public class StudentInfController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

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
    private TextField login;

    ObservableList<Students> studList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @FXML
    void searchstud(ActionEvent event) {
        if(login.getText() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findStudent");
            Students st = new Students();
            st.setLogin(login.getText());
            Connect.client.sendObject(st);
            try {
                studList.clear();
                ArrayList<Students> students = (ArrayList<Students>) Connect.client.readObject();
                System.out.println(students);
                studList.addAll(students);
                for (int i = 0; i < studList.size(); i++)
                    studTable.setItems(studList);
            } catch (Exception ex) {
                Logger.getLogger(ShowGroupsTimetable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        ArrayList<Students> students = (ArrayList<Students>) Connect.client.readObject();
        System.out.println(students);
        studList.addAll(students);
        studTable.setItems(studList);
        return studList;
    }
}
