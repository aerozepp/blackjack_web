package frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commands.Ace11Command;
import commands.AceChooseCommand;
import commands.AceCommand;
import commands.BlackjackCommand;
import commands.BustCheckCommand;
import commands.Command;
import commands.DealCommand;
import commands.DealerBlackjackCommand;
import commands.WinCommand;
import commands.DealerHitCommand;
import commands.GetDealerValueCommand;
import commands.HitCommand;
import commands.InsuranceCommand;
import commands.LostCommand;
import commands.ResetCommand;
import commands.StartCommand;
import commands.StayCommand;
import commands.TieCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		System.out.println("=======================");
		System.out.println("doGet()");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*doGet(request, response);*/
		System.out.println("=======================");
		System.out.println("doPost()");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("actionDo()");
		
		Command command = null;
		
		String uri = request.getRequestURI();
		System.out.println("URI: " + uri);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		String[] arrUri = uri.split("/");
		String realCommand = arrUri[arrUri.length - 1];
		
		if(realCommand.equals("start.do")){
			command = (Command) new StartCommand();
			command.execute(request, response);
			
		}else if(realCommand.equals("deal.do")){
			command = (Command) new DealCommand();
			command.execute(request, response);
			
		}else if(realCommand.equals("hit.do")){
			command = (Command) new HitCommand();
			command.execute(request, response);
			
		}else if(realCommand.equals("reset.do")){
			command = (Command) new ResetCommand();
			command.execute(request, response);
			
		}else if(realCommand.equals("stay.do")){
			command = (Command) new StayCommand();
			command.execute(request, response);
			
		}else if(realCommand.equals("dealer_hit.do")){
			command = (Command) new DealerHitCommand();
			command.execute(request, response);
		}else if(realCommand.equals("blackjack.do")){
			command = (Command) new BlackjackCommand();
			command.execute(request, response);
		}else if(realCommand.equals("win.do")){
			command = (Command) new WinCommand();
			command.execute(request, response);
		}else if(realCommand.equals("lost.do")){
			command = (Command) new LostCommand();
			command.execute(request, response);
		}else if(realCommand.equals("tie.do")){
			command = (Command) new TieCommand();
			command.execute(request, response);
		}else if(realCommand.equals("insurance.do")){
			command = (Command) new InsuranceCommand();
			command.execute(request, response);
		}else if(realCommand.equals("getDealerValue.do")){
			command = (Command) new GetDealerValueCommand();
			command.execute(request, response);
		}else if(realCommand.equals("dealerBlackjack.do")){
			command = (Command) new DealerBlackjackCommand();
			command.execute(request, response);
		}else if(realCommand.equals("ace.do")){
			command = (Command) new AceCommand();
			command.execute(request, response);
		}else if(realCommand.equals("aceChoose.do")){
			command = (Command) new AceChooseCommand();
			command.execute(request, response);
		}else if(realCommand.equals("bustCheck.do")){
			command = (Command) new BustCheckCommand();
			command.execute(request, response);
		}else if(realCommand.equals("ace11.do")){
			command = (Command) new Ace11Command();
			command.execute(request, response);
		}
		

	}

}
