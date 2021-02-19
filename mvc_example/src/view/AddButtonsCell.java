package view;

import control.DB_CRUD;
import example.Person;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AddButtonsCell extends TableCell<User, Boolean> {

	public AddButtonsCell(Stage primaryStage, TableView<User> table) {
		HBox paddedButtons = new HBox();
		Button btnUpdatePwd = new Button("Update Password");
		paddedButtons.getChildren().add(btnUpdatePwd);
		//showAddPersonDialog(primaryStage, table);
		btnUpdatePwd.setOnAction(e -> {
			//Label lblPasswordUpdateUName = new Label("Username: ");
			Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
			Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
			//TextField txtPasswordUpdateuserTextField = new TextField();
			PasswordField pwBoxOld = new PasswordField();
			//VBox vbPasswordUpdatetxtField = new VBox(txtPasswordUpdateuserTextField);
			//vbtxtField.setAlignment(Pos.CENTER);
			VBox vbPasswordUpdateold = new VBox(pwBoxOld);
			vbPasswordUpdateold.setAlignment(Pos.CENTER);
			
			PasswordField pwBoxNew = new PasswordField();
			VBox vbPasswordUpdatenew = new VBox(pwBoxOld);
			vbPasswordUpdatenew.setAlignment(Pos.CENTER);
            GridPane secondaryLayout = new GridPane();
			
			Button btnUpdate = new Button("Update");
			
			btnUpdate.setOnAction(ev -> {
				User data = new User();
				//data.getUserName(txtPasswordUpdateuserTextField.getText());
				data.setOldPassword(pwBoxOld.getText());
				data.setNewPassword(pwBoxNew.getText());
				DB_CRUD control = new DB_CRUD();
				int nextIndex = table.getSelectionModel().getSelectedIndex() + 1;
				table.getItems().addAll(nextIndex, control.buildData());
		        table.getSelectionModel().select(nextIndex);
				table.getSelectionModel().select(getTableRow().getIndex());

				boolean updateFlag = control.updateData(data);
				if(updateFlag==true) {
					Label lblsecondLabel = new Label("Password updated successfully!");
					 
	                StackPane secondaryLayout2 = new StackPane();
	                secondaryLayout2.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout2, 230, 100);
	               
	                
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
					Label lblsecondLabel = new Label("Couldn't update password!");
					 
	                StackPane secondaryLayout2 = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout2, 230, 100);
	 
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
		
		}

	private void showAddPersonDialog(Stage primaryStage, TableView<User> table) {
		
		
	}
}

