

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodels.ListItem;

/**
 * Servlet implementation class ListInsert
 */
@WebServlet("/ListInsert")
public class ListInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListInsert() {
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
		
		response.getWriter().append(
				"<div style=\"background-color:4281f5;padding:10px;display:flex;justify-content:space-between;\">"
				+ "	<h1 style=\"color:white; text-align:center;\">Add a New Task</h1>"
				+ "</div>"
			);
		
		DBConnectionBrown dbCon = new DBConnectionBrown(this.getServletContext());
		String dateStr = request.getParameter("date") + " " + request.getParameter("time");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		ListItem toAdd = new ListItem(dateStr, title, desc);
		boolean success = dbCon.insert(toAdd);
		response.getWriter().append("<div style=\"display:block;\">");
		if(success) {
			response.getWriter().append("Task added to database!");
			String htmlStr = String.format(
					"<div style=\"width:%s;padding:10px;margin:10px;border-radius:25px;"
					+ "box-shadow: 5px 5px 3px #aaaaaa;border: 1px solid #aaaaaa;\">"
					+ "	<h3>%s</h3>"
					+ "	<h4>%s</h4>"
					+ "	<p>%s</p>"
					+ "</div>",
					"90%", dateStr, title, desc
				);
			response.getWriter().append(htmlStr);
		}
		else {
			response.getWriter().append("<h2>Error adding task to the database!</h2>");
		}
		response.getWriter().append("<a href=\"ListView\">View List</a></div>");
	}

}
