import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import datamodels.ListItem;

public class DBConnectionBrown {
	
	static Connection connection = null;
	static ServletContext servletContext;
	
	static void getDBConnection() {
		UtilProp.loadProperty(servletContext);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public DBConnectionBrown(ServletContext context) {
		servletContext = context;
		getDBConnection();
	}
	
	public boolean insert(ListItem item) {
		String sql = String.format("insert into ToDoList (DUEDATE, TITLE, DESCRIPTION)" +
			"values('%s:00', '%s', '%s');",
		item.getDueDate(), item.getTitle(), item.getDesc());
		PreparedStatement prepState = null;
		if (connection != null) {
			try {
				prepState = connection.prepareStatement(sql);
				try {
					prepState.execute();
					return true;
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ResultSet getAll() {
		ResultSet rs = null;
		
		String sql = "select * from ToDoList order by DUEDATE ASC;";
		PreparedStatement prepState = null;
		if (connection != null) {
			try {
				prepState = connection.prepareStatement(sql);
				try {
					rs = prepState.executeQuery();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rs;
		
	}
	
	static String getURL() {
		return UtilProp.getProp("url");
	}
	static String getUserName() {
		return UtilProp.getProp("user");
	}
	static String getPassword() {
		return UtilProp.getProp("password");
	}
	
}