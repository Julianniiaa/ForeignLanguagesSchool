package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Courses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import util.Dialog;
import util.WindowChanger;

public class AddCourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> languageList = new ComboBox<>();

    @FXML
    private ComboBox<String> courseList = new ComboBox<>();

    @FXML
    void registrationCourse(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Courses course = new Courses();
            course.setLanguage(languageList.getValue());
            course.setCourse(courseList.getValue());
            Connect.client.sendMessage("addCourse");
            Connect.client.sendObject(course);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при записи курса"))
                Dialog.showAlertWithData();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return languageList.getValue().equals("") || courseList.getValue().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        languageList.getItems().addAll(
                "Английский",
                "Китайский",
                "Немецкий",
                "Французский",
                "Испанский",
                "Итальянский"
        );
        courseList.getItems().addAll(
                "Elementary",
                "Pre-Intermediate",
                "Intermediate",
                "Upper-Intermediate",
                "Advanced"
        );
    }
}
