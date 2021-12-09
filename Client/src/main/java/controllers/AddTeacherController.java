package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Dialog;

public class AddTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> category = new ComboBox();

    @FXML
    private ComboBox<String> language = new ComboBox();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dbWork.fxml"));

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
    void registrationTeacher(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Teacher teacher = new Teacher();
            teacher.setFirstname(firstName.getText());
            teacher.setLastname(lastName.getText());
            teacher.setLogin(login.getText());
            teacher.setPassword(password.getText());
            teacher.setCategory(category.getValue());
            teacher.setLanguage(language.getValue());
            Connect.client.sendMessage("registrationTeacher");
            Connect.client.sendObject(teacher);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("Incorrect Data"))
                Dialog.showAlertWithExistLogin();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("") ||
                    category.getValue().equals("") || language.getValue().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        category.getItems().addAll(
                "Высшая",
                "Первая",
                "Носитель языка"
        );
        language.getItems().addAll(
                "Английский",
                "Китайский",
                "Немецкий",
                "Французский",
                "Испанский",
                "Итальянский"
        );
    }
}
