package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.WindowChanger;

public class DbWorkController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button teacherButton;

    @FXML
    private Button chCourseButton;

    @FXML
    private Button timetableButton;

    @FXML
    private Button chTeacherButton;

    @FXML
    private Button courseButton;

    @FXML
    private Button chTimetableButton;

    @FXML
    private Button studButton;

    @FXML
    private Button delTeachButton;

    @FXML
    void addCourse(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), courseButton, "addCourse.fxml", "AddCourse", true);
    }

    @FXML
    void addTeacher(ActionEvent event) {
        teacherButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/addTeacher.fxml"));

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
    void addTimetable(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "addTimetable.fxml", "", false);
    }

    @FXML
    void changeCourse(ActionEvent event) {

    }

    @FXML
    void changeTeacher(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "changeTeacher.fxml", "", true);
    }

    @FXML
    void changeTimetable(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), timetableButton, "changeTimetable.fxml", "", false);
    }

    @FXML
    void deleteStud(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), studButton, "deleteStud.fxml", "", true);
    }

    @FXML
    void backToMenuAdmin(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuAdmin.fxml"));

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
    void deleteTeacher(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
