package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.WindowChanger;

public class MenuStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button regCourseButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button perstimetable;

    @FXML
    private Button persTeacherButton;

    @FXML
    private Button showTeachButton;

    @FXML
    private Button coursesButton;

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("studentInf");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), personalInfButton, "studentInformation.fxml", "", false);
    }

    @FXML
    void persTeacherTimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("studentsTeacherTimetable");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), perstimetable, "teacherTimetable.fxml", "", false);
    }

    @FXML
    void showCourses(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCourses");
        WindowChanger.changeWindow(getClass(), coursesButton, "showCourses.fxml", "", false);
    }

    @FXML
    void showTTimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showGrTeacher");
        WindowChanger.changeWindow(getClass(), showTeachButton, "showTeachers.fxml", "", false);
    }

    @FXML
    void showperstimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("studentTimetable");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), perstimetable, "studentTimetable.fxml", "", false);
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
    void regCourse(ActionEvent event) {
        regCourseButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/regCourse.fxml"));

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
    void initialize() {

    }
}
