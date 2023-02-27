package com.hostmdy.attendance.common.main_login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.hostmdy.attendance.admin.style.AdminStyleLoader;
import com.hostmdy.attendance.admin.view.AdminViewLoader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SigninController implements Initializable{

    @FXML
    private Button btnSignin;

    @FXML
    private ComboBox<String> cobRole;

    @FXML
    private AnchorPane imageHolder;

    @FXML
    private Label lblSignup;

    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private Label lblworngalern;

    @FXML
    private TextField tfEmail;
    
    private final SigninDAO signinDAO = new SigninDAO();
    
    private final AdminViewLoader adminViewLoader = new AdminViewLoader();
    
    private final AdminStyleLoader adminStyleLoader = new AdminStyleLoader();

    @FXML
    void processSignin(ActionEvent event) {
    
    	if(tfEmail == null || pfPassword == null || cobRole.getValue() == null) {
			lblworngalern.setText("Please fill out empty fields!");
    	}
    	else {
    		String email = tfEmail.getText().trim();
        	String password = pfPassword.getText().trim();
        	String role = cobRole.getValue().toLowerCase();
        	
        	boolean sigininOk = signinDAO.isCredentialsValid(email, password, role);
			if (sigininOk) {
				System.out.println("Sign in Success");
				if (role.equalsIgnoreCase("Admin")) {

					Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
					adminStage.hide();
					Parent adminMainUI = adminViewLoader.getAdminMainUI();
					Scene scene = new Scene(adminMainUI);
					scene.getStylesheets().add(adminStyleLoader.getStyleURL());
					adminStage.setScene(scene);
					adminStage.setTitle("Attendance Management System");
					Image image = new Image(getClass().getResourceAsStream("/com/hostmdy/attendance/common/main/attendancelogo.png"));
					adminStage.getIcons().add(image);
					adminStage.setMaximized(false);
					adminStage.show(); 
				} else {

				}
			} else {
				lblworngalern.setText("Email or password is wrong!");
			}
    		
    	}

    }

    @FXML
    void processSignupPage(MouseEvent event) throws IOException {
    	Stage signupStage = (Stage) ((Label)event.getSource()).getScene().getWindow();
    	signupStage.hide();    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/common/register/RegisterUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/common/style/style.css").toExternalForm());
		signupStage.setScene(scene);
		
		signupStage.setTitle("Attendance Management System");
		Image image = new Image(getClass().getResourceAsStream("/com/hostmdy/attendance/common/main/attendancelogo.png"));
		signupStage.getIcons().add(image);
				
		signupStage.show();


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> roleList = FXCollections.observableArrayList(
				"Admin","Teacher","Studnet"
				);
		
		cobRole.setItems(roleList);
	}

}
