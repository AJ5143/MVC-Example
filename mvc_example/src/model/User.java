package model;
import view.*;
import control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;


public class User {
	
	private String userName,oldPassword, newPassword;
	private int userID;
	private Button actionButton;
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String setOldPassword(String oldpass) {
		
		this.oldPassword = oldpass;
		return oldPassword;
		
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String setNewPassword(String newpassword) {
		this.newPassword = newpassword;
		return newPassword;
	}

	public Button getActionButton() {
		return actionButton;
	}

	public void setActionButton(Button actionButton) {
		this.actionButton = actionButton;
		
	}

	public User(String userName, String oldPassword, String newPassword, int userID) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.userID = userID;
	}

	public User() {
		
	}

	public User(String userName, String oldPassword, String newPassword, int userID, Button actionButton) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.userID = userID;
		this.actionButton = actionButton;
	}
public void onButtonClick(TableView<User> table) {
	// TODO Auto-generated method stub
	actionButton.setOnAction(e -> {
		ObservableList<User> userSelected, allUsers;
		allUsers = table.getItems();
		userSelected = table.getSelectionModel().getSelectedItems();
		userSelected.forEach(allUsers::remove);
		
	});
}

}
