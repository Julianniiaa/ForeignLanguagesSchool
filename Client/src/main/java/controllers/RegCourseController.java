package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Courses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import util.Dialog;

public class RegCourseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> languageList = new ComboBox();

    @FXML
    private ComboBox<String> courseList = new ComboBox();

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuStudent.fxml"));

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
    void registrationCourse(ActionEvent event) throws IOException {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Courses course = new Courses();
            course.setIdstud(Connect.id);
            course.setLanguage(languageList.getValue());
            course.setCourse(courseList.getValue());
            course.setBalance(300);
            Connect.client.sendMessage("regCourse");
            Connect.client.sendObject(course);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при записи на курс"))
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
