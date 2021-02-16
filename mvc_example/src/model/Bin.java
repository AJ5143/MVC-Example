package model;
import view.*;
import control.*;


public class Bin {
	private String userName,oldPassword, newPassword;
	private int userID;
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

	public Bin(String userName, String oldPassword, String newPassword, int userID) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.userID = userID;
	}

	public Bin() {
		// TODO Auto-generated constructor stub
	}

	
}
