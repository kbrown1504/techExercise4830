

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTask
 */
@WebServlet("/DeleteTask")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.getWriter().append("<div style=\"background-color:4281f5;padding:10px;display:flex;justify-content:space-between;\">"
			+ "	<h1 style=\"color:white; text-align:center;\">Delete Task</h1></div>");
		
		String dbId = request.getParameter("id");
		DBConnectionBrown dbCon = new DBConnectionBrown(this.getServletContext());
		ResultSet toDelete = dbCon.get(dbId);
		
		boolean success = dbCon.delete(dbId);
		if (success) {
			response.getWriter().append("Task deleted from the database.");
			try {
				if(toDelete.next()) {
					String date = toDelete.getString("duedate");
					String title = toDelete.getString("title");
					String desc = toDelete.getString("description");
					String htmlStr = String.format(
							"<div style=\"width:%s;padding:10px;margin:10px;border-radius:25px;"
							+ "box-shadow: 5px 5px 3px #aaaaaa;border: 1px solid #aaaaaa;\">"
							+ "	<h3>%s</h3>"
							+ "	<h4>%s</h4>"
							+ "	<p>%s</p>"
							+ "</div>",
							"90%", date, title, desc
						);
					response.getWriter().append(htmlStr);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			response.getWriter().append("ERROR: There was a problem deleting the task from the database.");
		}
		
		response.getWriter().append("<a href=\"ListView\">View List</a>");
	}

}
