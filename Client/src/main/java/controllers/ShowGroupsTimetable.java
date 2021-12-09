package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientWork.Connect;
import SchoolOrg.Groups;
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
import util.Check;
import util.Dialog;
import util.WindowChanger;

public class ShowGroupsTimetable implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Groups> grTimetable;

    @FXML
    private TableColumn<Groups, Integer> grNumberColumn;

    @FXML
    private TableColumn<Groups, Integer> classNumberColumn;

    @FXML
    private TableColumn<Groups, String> timeColumn;

    @FXML
    private TableColumn<Groups, String> languageColumn;

    @FXML
    private TableColumn<Groups, String> courseColumn;

    @FXML
    private TableColumn<Groups, String> teacherColumn;

    @FXML
    private TextField grNumber;

    ObservableList<Groups> GroupList = FXCollections.observableArrayList();

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        if (Connect.role.equals("admin")) {
            WindowChanger.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
        }
        else if(Connect.role.equals("teacher"))
            WindowChanger.changeWindow(getClass(), backButton, "menuTeacher.fxml", "", false);
        else
            WindowChanger.changeWindow(getClass(), backButton, "menuStudent.fxml", "", false);
    }

    @FXML
    void searchgroup(ActionEvent event) {
        if(grNumber.getText() == "" || Check.checkInt(grNumber.getText()))
            Dialog.showAlertWithNullInput();
        else {
            Connect.client.sendMessage("findGrTimetable");
            Groups gr = new Groups();
            gr.setGrNumber(Integer.parseInt(grNumber.getText()));
            Connect.client.sendObject(gr);
            try {
                GroupList.clear();
                ArrayList<Groups> timetableList = (ArrayList<Groups>) Connect.client.readObject();
                System.out.println(timetableList);
                for (int i = 0; i < timetableList.size(); i++) {
                    GroupList.add(new Groups(timetableList.get(i).getGrNumber(),
                            timetableList.get(i).getClassNumber(),
                            timetableList.get(i).getTime(),
                            timetableList.get(i).getLanguage(),
                            timetableList.get(i).getCourse(),
                            timetableList.get(i).getFirstname(),
                            timetableList.get(i).getLastname()));
                    grTimetable.setItems(GroupList);
                }
            } catch (Exception ex) {
                Logger.getLogger(ShowGroupsTimetable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grNumberColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getGrNumber()));
        classNumberColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getClassNumber()));
        timeColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTime()));
        languageColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLanguage()));
        courseColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCourse()));
        teacherColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        grTimetable.setItems(getGroup());
    }

    private ObservableList<Groups> getGroup() {
        ObservableList<Groups> GroupList = FXCollections.observableArrayList();
        ArrayList<Groups> timetableList = (ArrayList<Groups>) Connect.client.readObject();
        System.out.println(timetableList);
        for (int i = 0; i < timetableList.size(); i++) {
            GroupList.add(new Groups(timetableList.get(i).getGrNumber(),
                    timetableList.get(i).getClassNumber(),
                    timetableList.get(i).getTime(),
                    timetableList.get(i).getLanguage(),
                    timetableList.get(i).getCourse(),
                    timetableList.get(i).getFirstname(),
                    timetableList.get(i).getLastname()));
            grTimetable.setItems(GroupList);
        }
        return GroupList;
    }
}
