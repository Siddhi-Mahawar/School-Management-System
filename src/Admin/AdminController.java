package Admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    public TextField newId;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField email;
    @FXML
    public DatePicker dob;

    @FXML
    public TableView<StudentData> studentData;
    @FXML
    public TableColumn<StudentData,String> idColumn;
    @FXML
    public TableColumn<StudentData,String> firstNameColumn;
    @FXML
    public TableColumn<StudentData,String> lastNameColumn;
    @FXML
    public TableColumn<StudentData,String> emailColumn;
    @FXML
    public TableColumn<StudentData,String> dobColumn;

    public dbConnection db;
    public ObservableList<StudentData> data;

    public String sql = "SELECT * FROM student";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.db = new dbConnection();
    }
//
    @FXML
    public void loadStudentData(ActionEvent event) throws SQLException{


        try{

            Connection con =dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery(sql);

            while (rs.next()){

                this.data.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }



        }catch (SQLException e){
                System.out.println("Error is " + e);
        }

        this.idColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("email"));
        this.dobColumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("DOB"));


        this.studentData.setItems(null);
        this.studentData.setItems(data);
    }


    @FXML
    public void addStudent(ActionEvent event)throws SQLException{

        String sql1 = "INSERT INTO student(id,fname,lname,email,DOB) VALUES(?,?,?,?,?)";

        try{

            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql1);

            stmt.setString(1,this.newId.getText());
            stmt.setString(2,this.firstName.getText());
            stmt.setString(3,this.lastName.getText());
            stmt.setString(4,this.email.getText());
            stmt.setString(5,this.dob.getEditor().getText());

            stmt.execute();
            conn.close();

        }catch (SQLException e) {
            System.out.println("Error is " + e);
        }
    }

    @FXML
    public void clearFields(ActionEvent event){

        this.newId.setText("");
        this.firstName.setText("");
        this.lastName.setText("");
        this.email.setText("");
        this.dob.setValue(null);

    }


}
