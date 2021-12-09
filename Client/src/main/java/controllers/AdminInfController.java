package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Admin;
import SchoolOrg.Groups;
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
import util.WindowChanger;

public class AdminInfController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private TableView<Admin> infTable;

    @FXML
    private TableColumn<Admin, String> loginColumn;

    @FXML
    private TableColumn<Admin, String> passwordColumn;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    ObservableList<Admin> infList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @FXML
    void changeAdmin(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        passwordColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPassword()));
        infTable.setItems(getInf());
    }

    private ObservableList<Admin> getInf() {
        ObservableList<Admin> infList = FXCollections.observableArrayList();
        ArrayList<Admin> adminList = (ArrayList<Admin>) Connect.client.readObject();
        System.out.println(adminList);
        infList.addAll(adminList);
        return infList;
    }
}
