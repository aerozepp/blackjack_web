package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class WinCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		Dao dao = new Dao();
		int chip = dao.win();
		
		System.out.println("user chips:" + chip);
		
		
		String json = "{\"chip\":\""+chip+"\"}";
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
