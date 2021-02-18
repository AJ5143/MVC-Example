package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.*;
import view.*;
public class DB_CRUD {

	public boolean insertData(Bin data) 
	{
		boolean result = false;
		String query1 = "insert into first(Username,Password)" + "values(?,?)";
		String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
		try {
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
	        preparedStmt.setString(1, data.getUserName());
	        preparedStmt.setString(2, data.getNewPassword());
	        preparedStmt.execute();
	        result = true;
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean updateData(Bin data) {
		boolean result = false;	
		String query1 = "Update first SET Password = ? WHERE Password = ?";
		String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
		try {
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
			preparedStmt.setString(1, data.setNewPassword(data.getNewPassword()));
	        preparedStmt.setString(2, data.setOldPassword(data.getOldPassword()));
	        //preparedStmt.setString(3, data.getOldPassword());
	        preparedStmt.execute();
	        result = true;
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean deleteData(Bin data) {
		boolean result = false;
		String query1 = "Delete from first WHERE Username = ?";
		String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
		try {
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
			preparedStmt.setString(1, data.getUserName());
	        preparedStmt.execute();
	        result = true;
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public ObservableList<Bin> data;
	TableView<Bin> table;
	
	public ObservableList<Bin> buildData(){ 
	    data = FXCollections.observableArrayList();
	    try{      
	    	String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
	    	Connection con = DriverManager.getConnection(link);
	        String SQL = "Select * from first Order By UserID";            
	        ResultSet rs = con.createStatement().executeQuery(SQL);  
	        while(rs.next()){
	            Bin user = new Bin();
	            user.setUserID(rs.getInt("UserID"));                       
	            user.setUserName(rs.getString("UserName"));
	            user.setNewPassword(rs.getString("Password"));
	            data.add(user);                  
	        }
	        return data;
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    return null;
	}
	
}
