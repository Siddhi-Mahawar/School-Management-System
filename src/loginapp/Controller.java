package loginapp;



import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static loginapp.option.Admin;


public class Controller implements Initializable {


    LoginModal loginModal = new LoginModal();

    @FXML
    private Label dbStatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> comboBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(this.loginModal.isDatabaseConnected()){
            this.dbStatus.setText("Connected");
        }
        else
        {
            this.dbStatus.setText("Not Connected");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));


    }

    @FXML
    public void login(ActionEvent event){


        try
        {

            if(this.loginModal.isLogin(this.username.getText(),this.password.getText(),((option)this.comboBox.getValue()).toString())){

                System.out.println("Value found");
                Stage stage =(Stage)this.loginButton.getScene().getWindow();
                stage.close();

                switch (((option)this.comboBox.getValue()).toString()){

                        case "Admin" :
                            adminLogin();
                            break;
                        case "Student" :
                            studentLogin();
                            break;
                }

            }
            else
            {
                this.loginStatus.setText("Wrong Credentials");
            }



        }catch (Exception localException){

        }


    }

    public void studentLogin(){

        try{


            Stage userStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/students/studentFXML.fxml"));
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student DashBoard");
            userStage.setResizable(false);
            userStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void adminLogin(){

        try{


            Stage userStage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Admin DashBoard");
            userStage.setResizable(false);
            userStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
