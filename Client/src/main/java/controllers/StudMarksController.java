package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ClientWork.Connect;
import SchoolOrg.Role;
import SchoolOrg.Students;
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

public class StudMarksController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button setMarksButton;

    @FXML
    private TableView<Students> studTable;

    @FXML
    private TableColumn<Students, String> loginColumn;

    @FXML
    private TableColumn<Students, String> firstnameColumn;

    @FXML
    private TableColumn<Students, String> lastnameColumn;

    @FXML
    private TableColumn<Students, String> averageMarkColumn;

    @FXML
    private TableColumn<Students, Integer> groupColumn;

    @FXML
    private TableColumn<Students, Integer> paymentColumn;

    @FXML
    private TextField login;

    @FXML
    private TextField mark;

    ObservableList<Students> studList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        WindowChanger.changeWindow(getClass(), backButton, "menuTeacher.fxml", "", false);
    }

    @FXML
    void setMarks(ActionEvent event) throws IOException {
        if(login.getText().equals("") || mark.getText().equals("")) {
            Dialog.showAlertWithNullInput();
        }
        else {
            Students s = new Students();
            s.setLogin(login.getText());
            s.setAverageMark(mark.getText());
            Connect.client.sendMessage("addMarks");
            Connect.client.sendObject(s);
            Role r = new Role();
            r.setId(Connect.id);
            Connect.client.sendObject(r);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connect.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Неверный номер группы"))
                Dialog.showAlertWithData();
            else {
                Dialog.correctOperation();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firstnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        averageMarkColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getAverageMark()));
        groupColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getGroup()));
        paymentColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getPayment()));
        studTable.setItems(getStudent());
    }

    private ObservableList<Students> getStudent() {
        ObservableList<Students> studList = FXCollections.observableArrayList();
        ArrayList<Students> students = (ArrayList<Students>) Connect.client.readObject();
        System.out.println(students);
        studList.addAll(students);
        studTable.setItems(studList);
        return studList;
    }
}
