package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//입고 조회
@WebServlet("/importlist")
public class ImportList extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<fruitVo> importlist = DAO.importlist();
		
		System.out.println(DAO.i_productlist().size());

		request.setAttribute("list", importlist);
		
		
		request.setAttribute("title", "입고 조회");
		request.setAttribute("view", "importList.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);	
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
	}

}
