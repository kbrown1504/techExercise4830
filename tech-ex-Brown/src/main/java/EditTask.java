

import java.io.IOException;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.getWriter().append("<div style=\"background-color:4281f5;padding:10px;display:flex;justify-content:space-between;\">"
				+ "	<h1 style=\"color:white; text-align:center;\">Edit Task</h1></div>");
		
		String dbId = request.getParameter("id");
		DBConnectionBrown dbCon = new DBConnectionBrown(this.getServletContext());
		ResultSet toEdit = dbCon.get(dbId);
		
		try {
			if(toEdit.next()) {
				String date = toEdit.getString("duedate");
				
				String time = date.substring(11);
				System.out.println(date);
//				String month = date.substring(8,10);
//				String day = date.substring(5,7);
//				String year = date.substring(0,4);
//				date = month + day + year;
				String formDate = date.substring(0,10);
				
				System.out.println("\"" + date + "\"");
				String title = toEdit.getString("title");
				String desc = toEdit.getString("description");
				String htmlString = 
						String.format("<br><form action=\"EditTask\" method=\"POST\">"
						+ "Date: <input type=\"date\" name=\"date\" value=\"%s\">"
						+ "<br><br>"
						+ "Time: <input type=\"time\" name=\"time\" value=\"%s\">"
						+ "<br><br>"
						+ "	Title: <input type=\"text\" name=\"title\" value=\"%s\">"
						+ "<br><br>"
						+ "<textarea rows=\"6\" cols=\"50\" name=\"desc\">%s</textarea>"
						+ "<br><br>"
						+ "<input type=\"Submit\" value=\"Save Changes\">"
						+ "</form>", formDate, time, title, desc, desc);
				response.getWriter().append(htmlString);
			} else {
				response.getWriter().append("ERROR: Invalid task selected for editing.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().append("<br><a href=\"ListView\">View List</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.getWriter().append("<div style=\"background-color:4281f5;padding:10px;display:flex;justify-content:space-between;\">"
				+ "	<h1 style=\"color:white; text-align:center;\">Edit Task</h1></div>");
		
		String dbId = request.getParameter("id");
		DBConnectionBrown dbCon = new DBConnectionBrown(this.getServletContext());
	}

}
