// Work completed: All CRUD buttons working
// Insert Data bug EDIT : Solved!
// Table view query EDIT : Solved!
// Inserting and Updating data inside table EDIT: Solved!
// Buttons inside table EDIT: Solved!
// TableCRUD new class idea worth it? EDIT: Not.

package view;
import java.util.Iterator;

import com.sun.prism.impl.Disposer.Record;

import control.DB_CRUD;
//import example.TableViewDeleteSample.ButtonCell;
//import example.TableViewDeleteSample.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.User;


public class Main extends Application {
	
	public TableView<User> table;
	public TableView table2;
	User user = new User();
	DB_CRUD db = new DB_CRUD();
	public ObservableList<User> tableData = FXCollections.observableArrayList();
	public ObservableList<User>	totalData = FXCollections.observableArrayList();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) {
		try {			
			HBox hbuserDetails = new HBox();
			HBox hbpasswordDetails = new HBox();
			HBox hbamountDetails = new HBox();
			HBox hbsignin = new HBox();
			VBox vbFinal = new VBox();
			//VBox vb2 = new VBox();
			//hbamountDetails.setSpacing(10);
			hbuserDetails.setSpacing(8);
			hbpasswordDetails.setSpacing(10);
			hbamountDetails.setSpacing(20);
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
			
			Label lblamt = new Label("Amount:");
			VBox vbamt = new VBox(lblamt);
			vbamt.setAlignment(Pos.CENTER);
			
			TextField txtuserAmtField = new TextField();
			VBox vbtxtAmtField = new VBox(txtuserAmtField);
			vbtxtAmtField.setAlignment(Pos.CENTER);
			
			//Label lblforgotPass = new Label("forgot password");
			//VBox vbforgotPass = new VBox(lblforgotPass);
			//vbforgotPass.setAlignment(Pos.CENTER);

			Button btnInsert = new Button("Insert");
			//Button btnUpdatePwd = new Button("Update Password");
			//Button btnDelete = new Button("Delete");
			
			table = new TableView<User>();
			table2 = setTable2();
			table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
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
			
			
			TableColumn<User, Number> AmountColumn = new TableColumn<User, Number>("Amount");
			//AmountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
			AmountColumn.setOnEditCommit(
	                event -> event.getRowValue().setAmount(event.getNewValue().doubleValue()));
			AmountColumn.setCellValueFactory(data -> new ReadOnlyDoubleWrapper(data.getValue().getAmount()));
			//AmountColumn.setCellValueFactory(data -> data.getValue().amountProperty().asObject());

			
			@SuppressWarnings("rawtypes")
			TableColumn ActionColumn = new TableColumn<>("DELETE");
		    ActionColumn.setSortable(false);
		    
		    ActionColumn.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
	                ObservableValue<Boolean>>() {

	            @Override
	            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
	                return new SimpleBooleanProperty(p.getValue() != null);
	            }
	        });

	        //Adding the Button to the cell
	        ActionColumn.setCellFactory(
	                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

	            @Override
	            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
	                return new ButtonCell();
	            }
	        
	        });
	        @SuppressWarnings("rawtypes")
			TableColumn ActionColumn2 = new TableColumn<>("UPDATE");
		    ActionColumn2.setSortable(false);
		    
		    ActionColumn2.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
	                ObservableValue<Boolean>>() {

	            @Override
	            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
	                return new SimpleBooleanProperty(p.getValue() != null);
	            }
	        });

	        //Adding the Button to the cell
	        ActionColumn2.setCellFactory(
	                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

	            @Override
	            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
	                return new ButtonCell2();
	            }
	        
	        });
	        
		    
	        

		    //ActionColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("actionButton"));
		    
//		    TableColumn<User, Button> DeleteColumn = new TableColumn<>("Delete");
//		    DeleteColumn.setSortable(false);
//		    DeleteColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("deleteButton"));
//		    
//		    TableColumn<User, Button> UpdateColumn = new TableColumn<>("Update");
//		    UpdateColumn.setSortable(false);
//		    UpdateColumn.setCellValueFactory(new PropertyValueFactory<User, Button>("updateButton"));
		    
//		    ActionColumn.getColumns().addAll(DeleteColumn, UpdateColumn);
		    table.getColumns().setAll(UserIDColumn, UserNameColumn, PasswordColumn, AmountColumn , ActionColumn, ActionColumn2);
		   
			tableData.setAll(db.buildData());
			//tableData.add((User) db.buildTotal());
			//table.setItems(tableData);
			//tableData.addAll(db.buildTotal());
			
			
			//totalData.setAll(db.buildTotal());
			//table.setItems(totalData);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			refreshTable();
			
			
	        
		    //buttonPressed(table, primaryStage);
		   
			//user.onButtonClick(table);
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
				boolean specialFlagForAmount = true;
				boolean insertFlag = false;
			
				try {
					Double D = Double.valueOf((txtuserAmtField.getText()));
					data.setAmount(D);
					insertFlag = control.insertData(data);
				}
				catch(NumberFormatException n) {
					specialFlagForAmount = false;
					//control.deleteData(data);
					
					Label lblsecondLabel = new Label("Invalid input for Amount!");
	                StackPane secondaryLayout = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 250, 100);
	 
	                // New window (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("Second Stage");
	                newWindow.setScene(secondScene);
	 
	                // Set position of second window, related to primary window.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
	                
				}
				
				if(String.valueOf(data.getAmount()) == null)
				{
					control.deleteData(data);
					Label lblsecondLabel = new Label("Amount field can not be empty!");
	                StackPane secondaryLayout = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 250, 100);
	 
	                // New window (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("Second Stage");
	                newWindow.setScene(secondScene);
	 
	                // Set position of second window, related to primary window.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
					
				}
				if(txtuserTextField.getText().isBlank()==true || pwBox.getText().isBlank() == true)
				{
					Label lblsecondLabel = new Label("Please provide username and password both!");
	                StackPane secondaryLayout = new StackPane();
	                secondaryLayout.getChildren().add(lblsecondLabel);
	 
	                Scene secondScene = new Scene(secondaryLayout, 250, 100);
	 
	                // New window (Stage)
	                Stage newWindow = new Stage();
	                newWindow.setTitle("Second Stage");
	                newWindow.setScene(secondScene);
	 
	                // Set position of second window, related to primary window.
	                newWindow.setX(primaryStage.getX() + 200);
	                newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
				}
				
				if(specialFlagForAmount == true && txtuserAmtField.getText().isBlank() == false && txtuserTextField.getText().isBlank() == false && pwBox.getText().isBlank() == false ) 
				{
					txtuserTextField.clear();
					pwBox.clear();
					txtuserAmtField.clear();
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
			/*btnUpdatePwd.setOnAction(e -> {
				Label lblPasswordUpdateUID = new Label("Enter UserID");
				TextField txtPasswordUpdateuserTextField = new TextField();
				VBox vbPUTF = new VBox();
				
				Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
				Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
				PasswordField pwBoxOld = new PasswordField();
				vbtxtField.setAlignment(Pos.CENTER);
				VBox vbPasswordUpdateold = new VBox(pwBoxOld);
				vbPasswordUpdateold.setAlignment(Pos.CENTER);
				
				PasswordField pwBoxNew = new PasswordField();
				VBox vbPasswordUpdatenew = new VBox(pwBoxNew);
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
				
			});*/
			Region r = new Region();
			//Region r2 = new Region();
			VBox vbInsert = new VBox(btnInsert);
			vbInsert.setAlignment(Pos.CENTER);
			//VBox vbUpdate = new VBox(btnUpdatePwd);
			//vbInsert.setAlignment(Pos.CENTER);
			//VBox vbDelete = new VBox(btnDelete);
			vbInsert.setAlignment(Pos.CENTER);
			
			
			
			hbuserDetails.getChildren().addAll(vbuserName,vbtxtField);
			hbamountDetails.getChildren().addAll(vbamt,vbtxtAmtField);
			hbpasswordDetails.getChildren().addAll(vbpw,vbpwField);
			
			hbsignin.getChildren().addAll(vbInsert);
			
			hbuserDetails.setAlignment(Pos.CENTER);
			hbpasswordDetails.setAlignment(Pos.CENTER);
			hbamountDetails.setAlignment(Pos.CENTER);
			hbsignin.setAlignment(Pos.CENTER);
			
//			TabPane tabPane = new TabPane();
//			 Tab tab = new Tab();
//			 tab.setText("new tab");
//			 tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
//			 tabPane.getTabs().add(tab);
//			
			vbFinal.getChildren().addAll(r,hbuserDetails, hbamountDetails , hbpasswordDetails,hbsignin,table,table2);
			//double heightforScene = r.getHeight() + hbuserDetails.getHeight() + hbamountDetails.getHeight() + hbpasswordDetails.getHeight() + hbsignin.getHeight() + table.getHeight() + table2.getHeight();
			//double heightforScene = vbFinal.getHeight();
			
			System.out.println(vbFinal.getPrefHeight());
			Scene scene = new Scene(vbFinal,800, vbFinal.getPrefHeight());
			//table2.setMaxHeight(scene.getHeight() - (table.getHeight())*2 -hbuserDetails.getHeight() - hbamountDetails.getHeight());
			table2.setMaxHeight(0);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			
			
			//TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
//			ObservableList<TablePosition> posList = table.getSelectionModel().getSelectedCells();
////			TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedItems();
//			System.out.println(posList);
//			int row = 1;
//			DB_CRUD ctrl = new DB_CRUD();
//			ObservableList<User> userList = ctrl.buildData();
//			userList.set(row, user);
//			//userList = table.getSelectionModel().getSelectedItems();
//			User item = userList.get(row);
//			//User item = (User) table.getItems().get(row);
//			//int UID = userList.get(0).getUserID();
//			//String Uname = item.getUserName();
//			//String Pass = item.getNewPassword();
//			Button deletePerRow = userList.get(0).getActionButton();
//		    
//		    deletePerRow.setOnAction(e ->{
//				
//				//DB_CRUD control = new DB_CRUD();
//				boolean flagDeleteData = ctrl.deleteData(item);
//				if(flagDeleteData==true) {
//					Label lblsecondLabel = new Label("Row deleted successfully!");
//					 
//		            StackPane secondaryLayout2 = new StackPane();
//		            secondaryLayout2.getChildren().add(lblsecondLabel);
//
//		            Scene secondScene = new Scene(secondaryLayout2, 230, 100);
//
//		            // New window (Stage)
//		            Stage newWindow = new Stage();
//		            newWindow.setTitle("Second Stage");
//		            newWindow.setScene(secondScene);
//
//		            // Set position of second window, related to primary window.
//		            newWindow.setX(primaryStage.getX() + 200);
//		            newWindow.setY(primaryStage.getY() + 100);
//
//		            newWindow.show();
//		            
//		            refreshTable();
//				}
//				else {
//					Label lblsecondLabel = new Label("Couldn't delete row!");
//					 
//		            StackPane secondaryLayout2 = new StackPane();
//		            secondaryLayout2.getChildren().add(lblsecondLabel);
//
//		            Scene secondScene = new Scene(secondaryLayout2, 230, 100);
//
//		            // New window (Stage)
//		            Stage newWindow = new Stage();
//		            newWindow.setTitle("Second Stage");
//		            newWindow.setScene(secondScene);
//
//		            // Set position of second window, related to primary window.
//		            newWindow.setX(primaryStage.getX() + 200);
//		            newWindow.setY(primaryStage.getY() + 100);
//
//		            newWindow.show();
//				}
//			});

			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
//			Node header = table.lookup(".column-header-background");
//	        header.setOnContextMenuRequested(event -> {
//	            ContextMenu menu = new ContextMenu();
//	            menu.getItems().add(new MenuItem());
//	            menu.show(header, event.getScreenX(), event.getScreenY());
//	        });
			
			CustomMenuItem cmi;
	        ContextMenu cm = new ContextMenu();

	        // select all item
	        Label selectAll = new Label("Select all");
	        selectAll.addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @SuppressWarnings("rawtypes")
				@Override
	            public void handle(MouseEvent event) {
	                for( Object obj: table.getColumns()) {
	                    ((TableColumn) obj).setVisible(true);
	                }           
	                }

	        });
	        //cm.widthProperty().addListener(event -> selectAll.setPrefWidth(cm.getWidth()));
	        cmi = new CustomMenuItem(selectAll);
	        cmi.setHideOnClick(false);
	        cm.getItems().add(cmi);

	        // deselect all item
	        Label deselectAll = new Label("Deselect all");
	        deselectAll.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	            @Override
	            public void handle(MouseEvent event) {
	                for (Object obj : table.getColumns()) {
	                    ((TableColumn) obj).setVisible(false);
	                }
	            }

	        });
	        //cm.widthProperty().addListener(event -> deselectAll.setPrefWidth(cm.getWidth()));

	        cmi = new CustomMenuItem( deselectAll);
	        cmi.setHideOnClick(false);
	        cm.getItems().add( cmi);

	        // separator
	        cm.getItems().add(new SeparatorMenuItem());
	        
//        for(Iterator<Object> menuIterator = (Iterator<Object>) cm.getItems(); menuIterator.hasNext();)
//	        {
//	        	CustomMenuItem item = (CustomMenuItem) menuIterator.next();
//	        	CheckBox cbMenu = new CheckBox(item.getText());
//	        	cbMenu.selectedProperty().bindBidirectional(item.visibleProperty());
//	        	cmi = new CustomMenuItem(cbMenu);
//	            cmi.setHideOnClick(false);
//
//	            cm.getItems().add(cmi);
//
//	        }
//	        
	        // menu item for all columns
	        for( Object obj: table.getColumns()) {

	            TableColumn tableColumn = (TableColumn) obj; 

	            CheckBox cb = new CheckBox(tableColumn.getText());
	            cb.selectedProperty().bindBidirectional(tableColumn.visibleProperty());

	            cmi = new CustomMenuItem(cb);
	            cmi.setHideOnClick(false);

	            cm.getItems().add(cmi);
	        }

	        // set context menu
	        
	        table.setContextMenu(cm);
	        refreshTable();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void refreshTable()
	{
		tableData.clear();
		tableData.setAll(db.buildData());
		table.setItems(tableData);
		//tableData.addAll(db.buildTotal());
		
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TableView setTable2()
    {
    	TableView secondTable= new TableView<>();
    	
    	TableColumn UserIDColumn = new TableColumn<>("UserID");
	    UserIDColumn.setSortable(false);
	    
//	    UserIDColumn.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
//                ObservableValue<Boolean>>() {
//
//            @Override
//            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
//                return new SimpleBooleanProperty(p.getValue() != null);
//            }
//        });

        //Adding the Button to the cell
        UserIDColumn.setCellValueFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new UserIDTextFieldCell();
            }
        
        });
        
        TableColumn UsernameColumn = new TableColumn<>("Username");
	    UsernameColumn.setSortable(false);
	    
	    UsernameColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        UsernameColumn.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new UsernameTextFieldCell();
            }
        
        });
        
        TableColumn PasswordColumn = new TableColumn<>("Password");
	    PasswordColumn.setSortable(false);
	    
	    PasswordColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        PasswordColumn.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new PasswordTextFieldCell();
            }
        
        });
    	
        TableColumn TotalAmountColumn = new TableColumn<>("Amount");
	    TotalAmountColumn.setSortable(false);
	    
	    TotalAmountColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        TotalAmountColumn.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new AmountTotalTextFieldCell();
            }
        
        });
        TableColumn Search = new TableColumn<>("DELETE");
        TableColumn extra = new TableColumn<>("UPDATE");
        //UserIDColumn.prefWidthProperty().bind(table.getColumns().get(0).prefWidthProperty());
        
        secondTable.getColumns().setAll(UserIDColumn,UsernameColumn,PasswordColumn,TotalAmountColumn,Search,extra);
        secondTable.setPlaceholder(new Label());
        secondTable.setMaxHeight(-1);
        //secondTable.columnResizePolicyProperty(table.CONSTRAINED_RESIZE_POLICY);
       
		return secondTable;
    }
    
	 private class UserIDTextFieldCell extends TableCell<Record, Boolean> {
	    	final TextField txtUserID = new TextField();
	    	UserIDTextFieldCell(){
	    		ObservableList<User> data =  table.getItems();
	    		//table2.setItems(data);
	    		System.out.println(data);
	    		//System.out.println("Hiii");
	    		User currentUser = (User) UserIDTextFieldCell.this.getTableView().getItems().get(UserIDTextFieldCell.this.getIndex());
            	//remove selected item from the table list
				//data.setUserName(txtuserTextField.getText());
				//data.setNewPassword(pwBox.getText());
				//DB_CRUD control = new DB_CRUD();
				//boolean flagDeleteData = control.deleteData(currentUser);
	    		
	    		
	    		
	    		txtUserID.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	    		            if (oldValue != null && (newValue.length() < oldValue.length())) {
	    		                table.setItems(data);
	    		            }
	    		            String value = newValue.toLowerCase();
	    		            ObservableList<User> subentries = FXCollections.observableArrayList();

	    		            long count = table.getColumns().stream().count();
	    		            for (int i = 0; i < table.getItems().size(); i++) {
	    		                for (int j = 0; j < count; j++) {
	    		                    String entry = "" + table.getColumns().get(j).getCellData(i);
	    		                    if (entry.toLowerCase().contains(value)) {
	    		                        subentries.add(table.getItems().get(i));
	    		                        break;
	    		                    }
	    		                }
	    		            }
	    		            table.setItems(subentries);
	    		            //refreshTable();
	    		        });
	    }
	    		
	    }

	
	public static void main(String[] args) {
		launch(args);
	}
	
	private class ButtonCell extends TableCell<Record, Boolean> {
		Image image = new Image("Images/delete2.png");
		ImageView ivD = new ImageView(image);
		
		Button cellButton = new Button("",ivD);
	    ButtonCell(){
	    	
	    	
	    	
	    	ivD.setFitHeight(20);
	    	ivD.setFitWidth(20);
	    	
	    	//Action when the button is pressed
	    	
	        cellButton.setOnAction(new EventHandler<ActionEvent>(){
	        	
	            @Override
	            public void handle(ActionEvent t) {
	                // get Selected Item
	            	User currentUser = (User) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
	            	//remove selected item from the table list
					//data.setUserName(txtuserTextField.getText());
					//data.setNewPassword(pwBox.getText());
					DB_CRUD control = new DB_CRUD();
					boolean flagDeleteData = control.deleteData(currentUser);
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
		                //newWindow.setX(primaryStage.getX() + 200);
		                //newWindow.setY(primaryStage.getY() + 100);
		 
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
		                //newWindow.setX(primaryStage.getX() + 200);
		                //newWindow.setY(primaryStage.getY() + 100);
		 
		                newWindow.show();
					}

	            	
	            	
	            	
	            	
	            	//db.deleteData(currentUser);
	            	tableData.setAll(db.buildData());
	    			table.setItems(tableData);
	    			refreshTable();
	            }
	        });
	    }

	    //Display button if the row is not empty
	    @Override
	    protected void updateItem(Boolean t, boolean empty) {
	    	super.updateItem(t, empty);
	    	if(!empty){
	    	setGraphic(cellButton);
	    	}
	    	else{
	    	setGraphic(null);
	    	}
	    	}
	}
	private class ButtonCell2 extends TableCell<Record, Boolean> {
		final Button cellButton = new Button("Update");
	    ButtonCell2(){
	        
	    	//Action when the button is pressed
	        cellButton.setOnAction(new EventHandler<ActionEvent>(){

	            @Override
	            public void handle(ActionEvent t) {
	            	Label lblPasswordUpdateUID = new Label("Enter UserID");
	            	TextField txtuserTextField = new TextField();
	    			VBox vbtxtField = new VBox(txtuserTextField);
	    			vbtxtField.setAlignment(Pos.CENTER);
	            	Label lblPasswordUpdateOldPass = new Label("Enter old password: ");
					Label lblPasswordUpdateNewPass = new Label("Enter new password: ");
					PasswordField pwBoxOld = new PasswordField();
					//vbtxtField.setAlignment(Pos.CENTER);
					VBox vbPasswordUpdateold = new VBox(pwBoxOld);
					vbPasswordUpdateold.setAlignment(Pos.CENTER);
					
					PasswordField pwBoxNew = new PasswordField();
					VBox vbPasswordUpdatenew = new VBox(pwBoxOld);
					vbPasswordUpdatenew.setAlignment(Pos.CENTER);
	                GridPane secondaryLayout = new GridPane();
	            	User currentUser = (User) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());

					Button btnUpdate = new Button("Update");
					btnUpdate.setOnAction(ev -> {
						//User data = new User();
						DB_CRUD db = new DB_CRUD();
		            	//System.out.println(ButtonCell2.this.getIndex());
		            	//System.out.println(ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex()));  	
		            	
		            	//remove selected item from the table list
		            	//db.updateData(currentUser);
		            	
						//data.getUserName(txtPasswordUpdateuserTextField.getText());
						currentUser.setUserID(Integer.parseInt(txtuserTextField.getText()));
						currentUser.setOldPassword(pwBoxOld.getText());
						currentUser.setNewPassword(pwBoxNew.getText());
						//DB_CRUD control = new DB_CRUD();
						boolean flagUpdate = db.updateData(currentUser);
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
			                //newWindow.setX(primaryStage.getX() + 200);
			                //newWindow.setY(primaryStage.getY() + 100);
			 
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
			                //newWindow.setX(primaryStage.getX() + 200);
			                //newWindow.setY(primaryStage.getY() + 100);
			 
			                newWindow.show();
						}
						tableData.setAll(db.buildData());
		    			table.setItems(tableData);
		    			refreshTable();
					});
					
					secondaryLayout.add(lblPasswordUpdateUID, 0, 1);
					secondaryLayout.add(txtuserTextField, 1, 1);
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
	               // newWindow.setX(primaryStage.getX() + 200);
	               // newWindow.setY(primaryStage.getY() + 100);
	 
	                newWindow.show();
	            	refreshTable();
	            	
	                 //get Selected Item
	            	
	            }
	        });
	    }
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    //Display button if the row is not empty
	    @Override
	    protected void updateItem(Boolean t, boolean empty) {
	    	super.updateItem(t, empty);
	    	if(!empty){
	    	setGraphic(cellButton);
	    	}
	    	else{
	    	setGraphic(null);
	    	}
	    	}
	}
}
