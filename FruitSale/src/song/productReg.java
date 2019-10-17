package song;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//물품등록
@WebServlet("/reg")
public class productReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setAttribute("title", "물품등록");
		request.setAttribute("view", "productreg.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String p_name = request.getParameter("p_name");
		
		fruitVo vo = new fruitVo();
		
		vo.setP_name(p_name);
		
		int result = DAO.reg(vo);
		
		if(result == 0) {
			System.out.println("물품등록 실패");
			doGet(request, response);
		}
		if(result != 0) {
			System.out.println("물품등록 성공");
			response.sendRedirect("reg");
		}
	}

}
