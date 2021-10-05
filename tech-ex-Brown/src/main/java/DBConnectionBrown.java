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
	
	public void insert(ListItem item) {
		String sql = String.format("insert into ToDoList (DUEDATE, TITLE, DESCRIPTION)" +
			"values('%d-%d-%d %d:%d:00', '%s', '%s');",
			item.getDueDate().getYear(), item.getDueDate().getMonth(), item.getDueDate().getDay(),
			item.getDueDate().getHours(), item.getDueDate().getMinutes(), item.getTitle(), item.getDesc());
		PreparedStatement prepState = null;
		if (connection != null) {
			try {
				prepState = connection.prepareStatement(sql);
				try {
					prepState.execute();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getAll() {
		ResultSet rs = null;
		
		String sql = "select * from ToDoList order by DUEDATE DESC;";
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