package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.WindowChanger;

public class MenuTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button showGrButton;

    @FXML
    private Button showTeachButton;

    @FXML
    private Button perstimetable;

    @FXML
    private Button markButton;

    @FXML
    private Button coursesButton;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "main.fxml", "", false);
    }

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connect.client.sendMessage("teacherInf");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), personalInfButton, "teacherInformation.fxml", "", false);
    }

    @FXML
    void setmarkButton(ActionEvent event) throws IOException {
        Connect.client.sendMessage("studMarks");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), markButton, "studMarks.fxml", "", false);
    }

    @FXML
    void showCourses(ActionEvent event) throws IOException {
        Connect.client.sendMessage("showCourses");
        WindowChanger.changeWindow(getClass(), showGrButton, "showCourses.fxml", "", false);
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
    void showperstimetable(ActionEvent event) throws IOException {
        Connect.client.sendMessage("teacherTimetable");
        Role r = new Role();
        r.setId(Connect.id);
        Connect.client.sendObject(r);
        WindowChanger.changeWindow(getClass(), perstimetable, "teacherTimetable.fxml", "", false);
    }

    @FXML
    void initialize() {

    }
}
