package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import operation.Dao;

public class DealCommand extends Dao implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
		System.out.println("dealCommand()");
		
		int bet = Integer.parseInt(request.getParameter("bet"));
		Dao dao = new Dao();
		String json = dao.deal(bet);
		
		System.out.println("user betting: " + bet);
		System.out.println("user chips: " + Dao.user.getMoney());
		
	
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
