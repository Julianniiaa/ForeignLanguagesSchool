package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Timetable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import util.Check;
import util.Dialog;
import util.WindowChanger;

public class ChangeTimetableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField grNumber;

    @FXML
    private TextField classNumber;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private ComboBox<String> time = new ComboBox<>();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @FXML
    void changeTimetable(ActionEvent event) {
        if (checkInput() || Check.checkInt(time.getValue()))
            Dialog.showAlertWithNullInput();
        else {
            Timetable timetable = new Timetable();
            timetable.setId(Integer.parseInt(grNumber.getText()));
            timetable.setClassNumber(Integer.parseInt(classNumber.getText()));
            timetable.setTime(time.getValue());
            Connect.client.sendMessage("changeTimetable");
            Connect.client.sendObject(timetable);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("Ошибка при изменении расписания"))
                Dialog.showAlertWithExistLogin();
            else {
                Dialog.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return grNumber.getText().equals("") || classNumber.getText().equals("") ||
                    time.getValue().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        time.getItems().addAll(
                "10:00",
                "13:00",
                "16:00",
                "19:00",
                "21:00"
        );
    }
}
