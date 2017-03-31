package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class HitCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		Dao dao = new Dao();
		String[] path = dao.hit();

		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			for (int i = 0; i < path.length - 1; i++) {
				out.println("<img src=\"" + path[i] + "\">");
			}
			out.println("<div id=\"value\">"+path[path.length-1]+"</div>");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
