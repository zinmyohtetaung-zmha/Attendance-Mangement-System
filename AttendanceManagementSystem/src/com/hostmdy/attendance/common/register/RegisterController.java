package com.hostmdy.attendance.common.register;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

import com.hostmdy.attendance.admin.model.Admin;
import com.hostmdy.attendance.utility.crypto.PasswordEncoder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController implements Initializable{

    @FXML
    private Button btnRegister;

    @FXML
    private ComboBox<String> cobRole;
    
    @FXML
    private Label lblSignin;

    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private TextField tfAddress;


    @FXML
    private TextField tfCode;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFname;

    @FXML
    private TextField tfLname;

    @FXML
    private TextField tfMname;

    @FXML
    private TextField tfNrc;

    @FXML
    private TextField tfPhone;
    
    @FXML
    private Label lblworngalern;
    
    @FXML
    void processSigninPage(MouseEvent event) throws IOException {
    	Stage signupStage = (Stage) ((Label)event.getSource()).getScene().getWindow();
    	signupStage.hide();    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/common/main_login/SigninUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/common/style/style.css").toExternalForm());
		signupStage.setScene(scene);
		signupStage.show();

    }
    
    private final RegisterDAO registerDAO = new RegisterDAO();
    

    @FXML
    void processRegister(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
    	
    	if(tfFname == null || tfEmail == null || tfCode == null || pfPassword == null || tfNrc == null || tfPhone == null || tfAddress == null || cobRole.getValue() == null) {
    		lblworngalern.setText("Please fill out empty fields!");
    	}else {
    	
    	String firstname = tfFname.getText().trim();
    	String lastname = tfLname.getText().trim();
    	String middlename = tfMname.getText().trim();
    	String email = tfEmail.getText().trim();
    	String code = tfCode.getText().trim();
    	String rawPassword = pfPassword.getText().trim();
    	String nrc = tfNrc.getText().trim();
    	String phone = tfPhone.getText().trim();
    	String address = tfAddress.getText().trim();
    	String role = cobRole.getValue(); 
    	
    	String password = PasswordEncoder.encodePassword(rawPassword);
    	
    	Admin admin = new Admin(firstname, lastname, middlename, email, code, password, nrc, phone, address, role);
    	int rowEffect = this.registerDAO.createAdmin(admin);
    	if(rowEffect > 0) {
    		System.out.println("Create success admin");
    	}else {
    		System.out.println("Create fail");
    	}
    	
    	Stage adminStage = new Stage();
    	adminStage.hide();    	
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/hostmdy/attendance/common/main_login/SigninUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/hostmdy/attendance/common/style/style.css").toExternalForm());
		adminStage.setScene(scene);
		adminStage.show();
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> role = FXCollections.observableArrayList(
				"Admin","Teacher","Student"
				);
		cobRole.setItems(role);
		
	}

}
