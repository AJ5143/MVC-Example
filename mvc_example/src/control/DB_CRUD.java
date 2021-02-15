package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	        preparedStmt.setString(2, data.getPassword());
	        preparedStmt.execute();
	        result = true;
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void updateData(Bin data) {
				
		String query1 = "Update first SET Password = ? WHERE Username = ? AND Password = ?";
		String link = "jdbc:sqlite:C:\\Program Files\\DB Browser for SQLite\\First.db";
		try {
			Connection connection = DriverManager.getConnection(link);
			PreparedStatement preparedStmt = connection.prepareStatement(query1);
	        preparedStmt.setString(1, data.setNewpassword(data.getNewpassword());
	        preparedStmt.setString(2, data.getUserName());
	        preparedStmt.setString(3, data.getOldpassword());
	        preparedStmt.execute();
        }
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
