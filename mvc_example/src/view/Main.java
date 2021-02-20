// Work completed: All CRUD buttons working
// Insert Data bug EDIT : Solved!
// Table view query
// Inserting and Updating data inside table 
// Buttons inside table 
// TableCRUD new class idea worth it?

package view;
import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import control.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	public TableView<User> table;
	User user = new User();
	DB_CRUD db = new DB_CRUD();
	ObservableList<User> tableData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//TODO i have to complete this
			
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

			//Label lblforgotPass = new Label("forgot password");
			//VBox vbforgotPass = new VBox(lblforgotPass);
			//vbforgotPass.setAlignment(Pos.CENTER);

			Button btnInsert = new Button("Insert");
			Button btnUpdatePwd = new Button("Update Password");
			Button btnDelete = new Button("Delete");
			
			table = new TableView<User>();
			
			
			TableColumn<User, String> UserIDColumn = new TableColumn<User, String>("UserID");
			//UserNameColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("userName"));
			
			UserIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			UserIDColumn.setOnEditCommit(
	                event -> event.getRowValue().setUserID(Integer.parseInt(event.getNewValue())));
			UserIDColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getUserID())));
			
			
			TableColumn<User, String> UserNameColumn = new TableColumn<User, String>("UserName");
			//UserNameColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("userName"));
			
			UserNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			UserNameColumn.setOnEditCommit(
	                event -> event.getRowValue().setUserName(event.getNewValue()));
			UserNameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getUserName()));
			
			
			
			TableColumn<User, String> PasswordColumn = new TableColumn<User, String>("Password");
			//PasswordColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("newPassword"));
			PasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			PasswordColumn.setOnEditCommit(
	                event -> event.getRowValue().setNewPassword(event.getNewValue()));
			PasswordColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNewPassword()));
			
			TableColumn<User, Button> ActionColumn = new TableColumn<>("Action");
		    ActionColumn.setSortable(false);
		    ActionColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("actionButton"));
		    
//		    TableColumn<User, Button> DeleteColumn = new TableColumn<>("Delete");
//		    DeleteColumn.setSortable(false);
//		    DeleteColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("deleteButton"));
//		    
//		    TableColumn<User, Button> UpdateColumn = new TableColumn<>("Update");
//		    UpdateColumn.setSortable(false);
//		    UpdateColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("updateButton"));
		    
//		    ActionColumn.getColumns().addAll(DeleteColumn, UpdateColumn);
		    table.getColumns().setAll(UserIDColumn, UserNameColumn, PasswordColumn, ActionColumn);
		    
		    //buttonPressed(table, primaryStage);
		    tableData.setAll(db.buildData());
			table.setItems(tableData);

		    refreshTable();
			
			user.onButtonClick(table);
		    //define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
//		    ActionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
//		        @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<User, Boolean> features) {
//		          return new SimpleBooleanProperty(features.getValue() != null);
//		        }
//		      });
//		    
//		 // create a cell value factory with a button for each row in the table.
//		    ActionColumn.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
//		      @Override public TableCell<User, Boolean> call(TableColumn<User, Boolean> personBooleanTableColumn) {
//		        return new AddButtonsCell(primaryStage, table);
//		      }
//		    });
//			
			
//			table.getColumns().add(UserNameColumn);
//			table.getColumns().add(PasswordColumn);
			//table.setEditable(true);

			btnInsert.setOnAction(e->{
				User data = new User();
				data.setUserName(txtuserTextField.getText());
				data.setNewPassword(pwBox.getText());
				DB_CRUD control = new DB_CRUD();
				if(txtuserTextField.getText().isEmpty() || pwBox.getText().isEmpty())
				{
					Label lblsecondLabel = new Label("Please provide username and password both!");
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
				boolean insertFlag = control.insertData(data);
				if(insertFlag==true) 
				{
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
	                //table.refresh();
	                //table.getItems().clear();
	                table.setItems(tableData);
	                
	                
				}
				else 
				{
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
				
				refreshTable();
			});
			btnUpdatePwd.setOnAction(e -> {
				Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
				Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
				PasswordField pwBoxOld = new PasswordField();
				vbtxtField.setAlignment(Pos.CENTER);
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
					boolean flagUpdate = control.updateData(data);
					if(flagUpdate==true) {
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
		                refreshTable();
		                //table.setItems(tableData);
		                //table.refresh();
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
              //secondaryLayout.add(lblPasswordUpdateUName, 0, 1);
              //secondaryLayout.add(txtPasswordUpdateuserTextField, 1, 1);
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
				//Label lblDeleteUName = new Label("Username: ");
				//TextField txtUserTextField = new TextField();
				User data = new User();
				data.setUserName(txtuserTextField.getText());
				data.setNewPassword(pwBox.getText());
				DB_CRUD control = new DB_CRUD();
				boolean flagDeleteData = control.deleteData(data);
				if(flagDeleteData==true) {
					Label lblsecondLabel = new Label("Row deleted successfully!");
					 
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
	                
	                refreshTable();
				}
				else {
					Label lblsecondLabel = new Label("Couldn't delete row!");
					 
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
//				//GridPane secondaryLayout = new GridPane();
//				//secondaryLayout.add(lblDeleteUName, 0, 2);
//                //secondaryLayout.add(txtUserTextField, 1, 2);
//                secondaryLayout.setHgap(10);
//                secondaryLayout.setVgap(10);
//                Scene secondScene = new Scene(secondaryLayout, 300, 300);
// 
//                // New window (Stage)
//                Stage newWindow = new Stage();
//                newWindow.setTitle("Delete");
//                newWindow.setScene(secondScene);
// 
//                // Set position of second window, related to primary window.
//                newWindow.setX(primaryStage.getX() + 200);
//                newWindow.setY(primaryStage.getY() + 100);
// 
//                newWindow.show();
				
			});
			VBox vbInsert = new VBox(btnInsert);
			vbInsert.setAlignment(Pos.CENTER);
			VBox vbUpdate = new VBox(btnUpdatePwd);
			vbInsert.setAlignment(Pos.CENTER);
			VBox vbDelete = new VBox(btnDelete);
			vbInsert.setAlignment(Pos.CENTER);
			
			hbuserDetails.getChildren().addAll(vbuserName,vbtxtField);
			hbpasswordDetails.getChildren().addAll(vbpw,vbpwField);
			hbsignin.getChildren().addAll(vbInsert,vbUpdate,vbDelete);
			
			hbuserDetails.setAlignment(Pos.CENTER);
			hbpasswordDetails.setAlignment(Pos.CENTER);
			hbsignin.setAlignment(Pos.CENTER);
			
			vbFinal.getChildren().addAll(hbuserDetails,hbpasswordDetails,hbsignin,table);
			Scene scene = new Scene(vbFinal,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buttonPressed(TableView table, Stage primaryStage) {
		TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
//		TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedItems();
		int row = pos.getRow();
		refreshTable();
		ObservableList<User> userList;
		userList = table.getSelectionModel().getSelectedItems();
		//User item = (User) table.getSelectionModel().getSelectedItem();
		User item = (User) table.getItems().get(row);
		int UID = userList.get(0).getUserID();
		String Uname = userList.get(0).getUserName();
	
		String Pass = userList.get(0).getNewPassword();
		Button deletePerRow = userList.get(0).getActionButton();
		//User item = new User(Uname,Pass);
		
		deletePerRow.setOnAction(e ->{
			
			DB_CRUD control = new DB_CRUD();
			boolean flagDeleteData = control.deleteData(item);
			if(flagDeleteData==true) {
				Label lblsecondLabel = new Label("Row deleted successfully!");
				 
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
	            
	            refreshTable();
			}
			else {
				Label lblsecondLabel = new Label("Couldn't delete row!");
				 
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
		});
	}
	
			
	
		
	public void refreshTable()
	{
		tableData.clear();
		tableData.setAll(db.buildData());
		table.setItems(tableData);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
