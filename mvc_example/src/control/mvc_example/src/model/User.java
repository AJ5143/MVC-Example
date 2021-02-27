package model;
import view.*;
import control.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;


public class User {
	private String userName,oldPassword, newPassword;
	private int userID;
	private double amount;
	private double total;
	//private DoubleProperty amount;
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	//	public void setAmount(Double value) { DoubleProperty.set(value); }
//    public String getAmount() { return DoubleProperty().get(); }
//    public DoubleProperty amountProperty() { 
//        
//        return amount; 
//    }
	public User(String userName, String oldPassword, String newPassword, int userID, double amount) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.userID = userID;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

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

	public User(String userName, String oldPassword, String newPassword, int userID) {
		super();
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.userID = userID;
	}

	public User() {
		
	}

	
	
}
