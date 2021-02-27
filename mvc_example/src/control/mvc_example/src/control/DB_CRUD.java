package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import model.*;
import view.*;
public class DB_CRUD {
	
	public boolean insertData(User data)
	{
		boolean result = false;
		String query1 = "insert into first(Username,Password,Amount)" + "values(?,?,?)";
		String link = "jdbc:sqlite:C:\\Databases\\First.db";
		String mediator;
		Double mediator2 = data.getAmount();
	
		mediator = Double.toString(data.getAmount());
		//System.out.println(mediator + "aaaaa");
		try {
			if(data.getUserName().isEmpty() || data.getNewPassword().isEmpty()) {
				result = false;
			}
			
			else if(mediator2.isNaN())
			{
				result = false;
			}
			else {
				
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
	        preparedStmt.setString(1, data.getUserName());
	        preparedStmt.setString(2, data.getNewPassword());
	        preparedStmt.setDouble(3, data.getAmount());
	        preparedStmt.execute();
	        result = true;
	        preparedStmt.close();
	        connection.close();
			}
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean updateData(User data){
		boolean result = false;	
		String query1 = "Update first SET Password = ? WHERE Password = ?";
		String link = "jdbc:sqlite:C:\\Databases\\First.db";
		
		try {
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
			preparedStmt.setString(1, data.setNewPassword(data.getNewPassword()));
	        preparedStmt.setString(2, data.setOldPassword(data.getOldPassword()));
	        //preparedStmt.setString(3, data.getOldPassword());
	        preparedStmt.execute();
	        result = true;
	        preparedStmt.close();
	        connection.close();
	        
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean deleteData(User data) {
		boolean result = false;
		String query1 = "Delete from first WHERE Username = ? AND Password = ?";
		String link = "jdbc:sqlite:C:\\Databases\\First.db";
		
		try {
			Connection connection = DriverManager.getConnection(link);
			//Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
			preparedStmt.setString(1, data.getUserName());
			preparedStmt.setString(2, data.getNewPassword());
			
	        preparedStmt.execute();
	        preparedStmt.close();
	        result = true;
	        connection.close();
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public ObservableList<User> data;
	TableView<User> table;
	
	public ObservableList<User> buildData() { 
	    data = FXCollections.observableArrayList();
	    
	    String link = "jdbc:sqlite:C:\\Databases\\First.db";
    	
	    try{      
	    	Connection con = DriverManager.getConnection(link);
	        String SQL = "Select * from first Order By UserID";            
	        ResultSet rs = con.createStatement().executeQuery(SQL);  
	        while(rs.next()){
	            User user = new User();
	            user.setUserID(rs.getInt("UserID"));                       
	            user.setUserName(rs.getString("UserName"));
	            user.setNewPassword(rs.getString("Password"));
	            user.setAmount(rs.getDouble("Amount"));
	            data.add(user);                  
	        }
	        rs.close();
	        con.close();
	        return data;
	        
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    
	    return null;
	}

//	public User buildTotal() {
//		String link = "jdbc:sqlite:C:\\Databases\\First.db";
//		User user = new User();
//	    try{      
//	    	Connection con = DriverManager.getConnection(link);
//	        String SQL = "Select SUM(Amount) as SUM_AMOUNT from first";            
//	        ResultSet rs = con.createStatement().executeQuery(SQL);  
//	        while(rs.next()){
//	            
//	            //user.setUserID(rs.getInt("UserID"));                       
//	            user.setUserName(null);
//	            user.setNewPassword(null);
//	            user.setAmount(rs.getDouble("SUM_AMOUNT"));              
//	        }
//	        con.close();
//	        return user;
//	        
//	    }
//	    catch(Exception e){
//	          e.printStackTrace();
//	          System.out.println("Error on Building Data");            
//	    }
//	    return user;
//	}
}
	