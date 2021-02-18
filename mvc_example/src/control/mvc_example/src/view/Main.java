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
	
	public TableView<Bin> table;
	
	DB_CRUD db = new DB_CRUD();
	
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
			
			table = new TableView<Bin>();
			ObservableList<Bin> tableData = FXCollections.observableArrayList();
			
			TableColumn<Bin, String> UserIDColumn = new TableColumn<Bin, String>("UserID");
			//UserNameColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("userName"));
			
			UserIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			UserIDColumn.setOnEditCommit(
	                event -> event.getRowValue().setUserID(Integer.parseInt(event.getNewValue())));
			UserIDColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getUserID())));
			
			
			TableColumn<Bin, String> UserNameColumn = new TableColumn<Bin, String>("UserName");
			//UserNameColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("userName"));
			
			UserNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			UserNameColumn.setOnEditCommit(
	                event -> event.getRowValue().setUserName(event.getNewValue()));
			UserNameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getUserName()));
			
			
			
			TableColumn<Bin, String> PasswordColumn = new TableColumn<Bin, String>("Password");
			//PasswordColumn.setCellValueFactory(new PropertyValueFactory<Bin, String>("newPassword"));
			PasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			PasswordColumn.setOnEditCommit(
	                event -> event.getRowValue().setNewPassword(event.getNewValue()));
			PasswordColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNewPassword()));
			
			TableColumn<Bin, Boolean> ActionColumn = new TableColumn<>("Action");
		    ActionColumn.setSortable(false);
		    
		    
		    //define a simple boolean cell value for the action column so that the column will only be shown for non-empty rows.
		    ActionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bin, Boolean>, ObservableValue<Boolean>>() {
		        @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Bin, Boolean> features) {
		          return new SimpleBooleanProperty(features.getValue() != null);
		        }
		      });
		    
		 // create a cell value factory with an add button for each row in the table.
//		    ActionColumn.setCellFactory(new Callback<TableColumn<Bin, Boolean>, TableCell<Bin, Boolean>>() {
//		      @Override public TableCell<Bin, Boolean> call(TableColumn<Bin, Boolean> personBooleanTableColumn) {
//		        return new AddButtonsCell(primaryStage, table);
//		      }
//		    });
			
			table.getColumns().setAll(UserIDColumn, UserNameColumn, PasswordColumn, ActionColumn);
//			table.getColumns().add(UserNameColumn);
//			table.getColumns().add(PasswordColumn);
			//table.setEditable(true);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tableData.setAll(db.buildData());
			table.setItems(tableData);
//			public void buildData(){ 
//				   // tableData = FXCollections.observableArrayList();
//				    try{      
//				    	String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
//				    	Connection con = DriverManager.getConnection(link);
//				        String SQL = "Select * from first Order By Username";            
//				        ResultSet rs = con.createStatement().executeQuery(SQL);  
//				        while(rs.next()){
//				            Bin user = new Bin();
//				            user.setUserID(rs.getInt("UserID"));                       
//				            user.setUserName(rs.getString("UserName"));
//				            user.setNewPassword(rs.getString("Password"));
//				            tableData.add(user);                  
//				        }
//				        table.setItems(tableData);
//				   catch(Exception e){ 
//					   
//				   }
//				   }
//				}

			btnInsert.setOnAction(e->{
				Bin data = new Bin();
				data.setUserName(txtuserTextField.getText());
				data.setNewPassword(pwBox.getText());
				DB_CRUD control = new DB_CRUD();
				boolean insertFlag = control.insertData(data);
				
				if(insertFlag==true) {
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
				//Label lblPasswordUpdateUName = new Label("Username: ");
				Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
				Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
				//TextField txtPasswordUpdateuserTextField = new TextField();
				PasswordField pwBoxOld = new PasswordField();
				//VBox vbPasswordUpdatetxtField = new VBox(txtPasswordUpdateuserTextField);
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
					//data.getUserName(txtPasswordUpdateuserTextField.getText());
					data.setOldPassword(pwBoxOld.getText());
					data.setNewPassword(pwBoxNew.getText());
					DB_CRUD control = new DB_CRUD();
					control.updateData(data);
					if(control.updateData(data)==true) {
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
				Bin data = new Bin();
				data.setUserName(txtuserTextField.getText());
				DB_CRUD control = new DB_CRUD();
				control.deleteData(data);
				if(control.deleteData(data)==true) {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
