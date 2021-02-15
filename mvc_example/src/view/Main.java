package view;
import model.*;
import control.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox hbuserDetails = new HBox();
			HBox hbpasswordDetails = new HBox();
			HBox hbsignin = new HBox();
			VBox vbFinal = new VBox();
			//VBox vb2 = new VBox();
			hbuserDetails.setSpacing(10);
			hbpasswordDetails.setSpacing(10);
			hbsignin.setSpacing(10);
			vbFinal.setSpacing(10);
			//vb2.setSpacing(10);
			Text scenetitle = new Text("Welcome");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			
			Label lbluserName = new Label("User Name:");
			VBox vbuserName = new VBox(lbluserName);
			vbuserName.setAlignment(Pos.CENTER);
			TextField txtuserTextField = new TextField();
			VBox vbtxtField = new VBox(txtuserTextField);
			vbtxtField.setAlignment(Pos.CENTER);
			
			Label lblpw = new Label("Password:");
			VBox vbpw = new VBox(lblpw);
			vbpw.setAlignment(Pos.CENTER);

			PasswordField pwBox = new PasswordField();
			VBox vbpwField = new VBox(pwBox);
			vbpwField.setAlignment(Pos.CENTER);

			Label lblforgotPass = new Label("forgot password");
			VBox vbforgotPass = new VBox(lblforgotPass);
			vbforgotPass.setAlignment(Pos.CENTER);

			Button btnInsert = new Button("Insert");
			Button btnUpdatePwd = new Button("Update Password");
			Button btnDelete = new Button("Delete");
			btnInsert.setOnAction(e->{
				Bin data = new Bin();
				data.setUserName(txtuserTextField.getText());
				data.setPassword(pwBox.getText());
				DB_CRUD control = new DB_CRUD();
				control.insertData(data);
				if(control.insertData(data)==true) {
					Label lblsecondLabel = new Label("Data inserted successfully!");
					 
	                StackPane secondaryLayout = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 230, 100);
	 
	                // New window (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("Second Stage");
	                newWindow.setScene(secondScene);
	 
	                // Set position of second window, related to primary window.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
				}
				else {
					Label lblsecondLabel = new Label("Couldn't insert data!");
					 
	                StackPane secondaryLayout = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 230, 100);
	 
	                // New window (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("Second Stage");
	                newWindow.setScene(secondScene);
	 
	                // Set position of second window, related to primary window.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
				}
				
			});
			btnUpdatePwd.setOnAction(e -> {
				Label lblPasswordUpdateUName = new Label("Username: ");
				Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
				Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
				TextField txtPasswordUpdateuserTextField = new TextField();
				PasswordField pwBoxOld = new PasswordField();
				VBox vbPasswordUpdatetxtField = new VBox(txtPasswordUpdateuserTextField);
				vbtxtField.setAlignment(Pos.CENTER);
				VBox vbPasswordUpdateold = new VBox(pwBoxOld);
				vbPasswordUpdateold.setAlignment(Pos.CENTER);
				
				PasswordField pwBoxNew = new PasswordField();
				VBox vbPasswordUpdatenew = new VBox(pwBoxOld);
				vbPasswordUpdatenew.setAlignment(Pos.CENTER);
                GridPane secondaryLayout = new GridPane();
				
				Button btnUpdate = new Button("Update");
				btnUpdate.setOnAction(ev -> {
					Bin data = new Bin();
					data.getUserName(txtPasswordUpdateuserTextField.getText());
					data.getPassword(pwBoxOld.getText());
					data.setPassword(pwBoxNew.getText());
					DB_CRUD control = new DB_CRUD();
					control.updateData(data);
					
				});
				
				
				
                
                secondaryLayout.add(lblPasswordUpdateUName, 0, 1);
                secondaryLayout.add(txtPasswordUpdateuserTextField, 1, 1);
                secondaryLayout.add(lblPasswordUpdateOldPass, 0, 2);
                secondaryLayout.add(pwBoxOld, 1, 2);
                secondaryLayout.add(lblPasswordUpdateNewPass, 0, 3);
                secondaryLayout.add(pwBoxNew, 1, 3);
                secondaryLayout.add(btnUpdate, 1, 4);
                secondaryLayout.setHgap(10);
                secondaryLayout.setVgap(10);
                Scene secondScene = new Scene(secondaryLayout, 300, 300);
 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Update Password");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);
 
                newWindow.show();
				
				
				
				
				
				
				
				
				
			});
			btnDelete.setOnAction(e -> {
				
				
				
				
				
				
				
				
				
			});
			VBox vbInsert = new VBox(btnInsert);
			vbInsert.setAlignment(Pos.CENTER);
			VBox vbUpdate = new VBox(btnUpdatePwd);
			vbInsert.setAlignment(Pos.CENTER);
			VBox vbDelete = new VBox(btnDelete);
			vbInsert.setAlignment(Pos.CENTER);
			
			hbuserDetails.getChildren().addAll(vbuserName,vbtxtField);
			hbpasswordDetails.getChildren().addAll(vbpw,vbpwField);
			hbsignin.getChildren().addAll(vbforgotPass,vbInsert,vbUpdate,vbDelete);
			vbFinal.getChildren().addAll(hbuserDetails,hbpasswordDetails,hbsignin);
			Scene scene = new Scene(vbFinal,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
