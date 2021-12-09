package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientWork.Connect;
import SchoolOrg.Courses;
import SchoolOrg.Groups;
import SchoolOrg.Students;
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
import javafx.scene.control.TextField;
import util.Dialog;
import util.WindowChanger;

public class ShowCoursesController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Courses> infTable;

    @FXML
    private TableColumn<Courses, String> languageColumn;

    @FXML
    private TableColumn<Courses, String> courseColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField language;

    ObservableList<Courses> courseList = FXCollections.observableArrayList();

    @FXML
    void searchCourse(ActionEvent event) {
        if(language.getText() == "")
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findCourses");
            Courses c = new Courses();
            c.setLanguage(language.getText());
            Connect.client.sendObject(c);
            try {
                courseList.clear();
                ArrayList<Courses> courses = (ArrayList<Courses>) Connect.client.readObject();
                System.out.println(courses);
                courseList.addAll(courses);
                infTable.setItems(courseList);
            } catch (Exception ex) {
                Logger.getLogger(ShowGroupsTimetable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        if (Connect.role.equals("teacher"))
            WindowChanger.changeWindow(getClass(), backButton, "menuTeacher.fxml", "", false);
        else if(Connect.role.equals("student"))
            WindowChanger.changeWindow(getClass(), backButton, "menuStudent.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLanguage()));
        courseColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCourse()));
        infTable.setItems(getTeacher());
    }

    private ObservableList<Courses> getTeacher() {
        ObservableList<Courses> courseList = FXCollections.observableArrayList();
        ArrayList<Courses> courses = (ArrayList<Courses>) Connect.client.readObject();
        System.out.println(courses);
        courseList.addAll(courses);
        infTable.setItems(courseList);
        return courseList;
    }
}
