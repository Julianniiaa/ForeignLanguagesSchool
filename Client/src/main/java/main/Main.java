package main;

import ClientWork.Client;
import ClientWork.Connect;
import SchoolOrg.Students;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Autorization");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Connect.client = new Client("127.0.0.2", "9006");
//        Connect.client.sendMessage("hello from client");
//        Students student = new Students();
//        student.setFirstname("1");
//        student.setLastname("1");
//        student.setLogin("1");
//        student.setPassword("1");
//        Connect.client.sendMessage("registrationStudent");
//        Connect.client.sendObject(student);
        launch();
    }
}