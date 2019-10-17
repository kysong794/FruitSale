package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//물품 출고 조회
@WebServlet("/exportlist")
public class ExportList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<fruitVo> exportlist = DAO.exportlist();
		
		request.setAttribute("list", exportlist);
			
		request.setAttribute("title", "출고 조회");
		request.setAttribute("view", "exportList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
