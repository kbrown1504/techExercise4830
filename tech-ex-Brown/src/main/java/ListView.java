import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.ListItem;

/**
 * Servlet implementation class ListView
 */
@WebServlet("/ListView")
public class ListView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Get and display List!
		response.setContentType("text/html");
		response.getWriter().append(
			"<div style=\"background-color:afafaf;padding:10px;display:flex;justify-content:space-between;\">"
			+ "	<h1 style=\"color:white; text-align:center;\">My To-Do List</h1>"
			+ "<button onclick=\"location.href='new_task.html'\">Add Task</button>"
			//Was going to use an anchor here, but found a way to have buttons access links.
			//https://stackoverflow.com/questions/2906582/how-to-create-an-html-button-that-acts-like-a-link
			+ "</div>"
		);
		DBConnectionBrown dbCon = new DBConnectionBrown(this.getServletContext());
		ResultSet list = dbCon.getAll();
		try {
			while(list.next()) {
				String dateStr = list.getString("duedate");
				String title = list.getString("title");
				String desc = list.getString("description");
				
				String htmlStr = String.format(
					"<div style=\"width:%s;float:left;padding:10px;margin:10px;border-radius:25px;"
					+ "box-shadow: 5px 5px 3px #aaaaaa;border: 1px solid #aaaaaa;\">"
					+ "	<h3>%s</h3>"
					+ "	<h4>%s</h4>"
					+ "	<p>%s</p>"
					+ "	<button onclick=\"location.href='edit_task.html'\">Edit</button>"
					+ "	<button>Delete</button>"
					+ "</div>",
					"90%", dateStr, title, desc
				);
				
				response.getWriter().append(htmlStr);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
