package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.Dialog;

public class ChangeTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idCh;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private ComboBox<String> category = new ComboBox<>();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void changeTeacher(ActionEvent event) {
        if (checkInput())
            Dialog.showAlertWithNullInput();
        else {
            Teacher teacher = new Teacher();
            teacher.setLastlogin(idCh.getText());
            teacher.setFirstname(firstName.getText());
            teacher.setLastname(lastName.getText());
            teacher.setLogin(login.getText());
            teacher.setPassword(password.getText());
            teacher.setCategory(category.getValue());
            Connect.client.sendMessage("changeTeacher");
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
            return  idCh.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("") ||
                    category.getValue().equals("");
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
    }
}
