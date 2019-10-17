package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//물품 수정
@WebServlet("/mod")
public class ProductMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String p_no = request.getParameter("p_no");
		List<fruitVo> productlist = DAO.productlist(p_no);
		
		request.setAttribute("list", productlist);
		
		request.setAttribute("title", "물품 정보 수정");
		request.setAttribute("view", "productMod.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String p_no = request.getParameter("p_no");
		String p_name = request.getParameter("p_name");
		
		fruitVo vo = new fruitVo();
		
		vo.setP_no(p_no);
		vo.setP_name(p_name);
		
		int result = DAO.mod(vo);
		
		if(result == 0) {
			System.out.println("수정 실패");
			doGet(request, response);
		}
		if(result != 0) {
			System.out.println("수정 성공");
			response.sendRedirect("productlist");
		}
	
	}

}
