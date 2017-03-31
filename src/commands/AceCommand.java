package commands;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class AceCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		Dao dao = new Dao();
		ArrayList<String> path = dao.ace();
		String json = null;

		int length = path.size();

		switch (length) {

		case 1:
			json = "{\"path1\":\"" + (String)path.get(0) + "\"}";
			break;
		case 2:
			json = "{\"path1\":\"" + (String)path.get(0) + "\"}";
			break;
		case 3:
			json = "{\"path1\":\"" + (String)path.get(0) + "\", \"path2\":\"" + (String)path.get(1) + "\" }";
			break;
		case 4:
			json = "{\"path1\":\"" + (String)path.get(0) + "\", \"path2\":\"" + (String)path.get(1) + "\", \"path3\":\"" + (String)path.get(2)
					+ "\" }";
			break;
		}

		response.setContentType("application/json");

		try {
			PrintWriter out = response.getWriter();

			out.println(json);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
