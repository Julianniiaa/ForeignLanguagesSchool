package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Groups;
import SchoolOrg.Teacher;
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

public class TeacherInfController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Teacher> infTable;

    @FXML
    private TableColumn<Teacher, String> loginColumn;

    @FXML
    private TableColumn<Teacher, String> firstnameColumn;

    @FXML
    private TableColumn<Teacher, String> lastnameColumn;

    @FXML
    private TableColumn<Teacher, String> categoryColumn;

    @FXML
    private TableColumn<Teacher, String> languageColumn;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuTeacher.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firstnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        categoryColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCategory()));
        languageColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLanguage()));
        infTable.setItems(getTeacher());
    }

    private ObservableList<Teacher> getTeacher() {
        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        ArrayList<Teacher> teachers = (ArrayList<Teacher>) Connect.client.readObject();
        System.out.println(teachers);
        teacherList.addAll(teachers);
        infTable.setItems(teacherList);
        return teacherList;
    }
}
