

import java.io.IOException;
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
				String formDate = date.substring(0,10);
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
						+ "<input type=\"text\" value=\"%s\" name=\"id\" style=\"display:none;\">"
						+ "</form>", formDate, time, title, desc, dbId);
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
		
		String newDate = request.getParameter("date") + " " + request.getParameter("time");
		String newTitle = request.getParameter("title");
		String newDesc = request.getParameter("desc");
		boolean success = dbCon.update(dbId, newDate, newTitle, newDesc);
		if (success) {
			response.getWriter().append("Successfully updated record!");
			String htmlStr = String.format(
					"<div style=\"width:%s;padding:10px;margin:10px;border-radius:25px;"
					+ "box-shadow: 5px 5px 3px #aaaaaa;border: 1px solid #aaaaaa;\">"
					+ "	<h3>%s</h3>"
					+ "	<h4>%s</h4>"
					+ "	<p>%s</p>"
					+ "</div>",
					"90%", newDate, newTitle, newDesc);
			response.getWriter().append(htmlStr);
		}
		else {
			response.getWriter().append("ERROR: Could not update record.");
		}
		response.getWriter().append("<br><a href=\"ListView\">View List</a>");
	}

}
