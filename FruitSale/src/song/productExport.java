package song;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//물품 출고
@WebServlet("/export")
public class productExport extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<fruitVo> list = DAO.i_productlist();
		
		request.setAttribute("list", list);
		
		request.setAttribute("title", "물품 출고");
		request.setAttribute("view", "productexport.jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	
		fruitVo vo = new fruitVo();
		
		String p_no = request.getParameter("p_no");
		String e_cnt = request.getParameter("e_cnt");
		
		System.out.println("p_no:"+p_no);
		System.out.println("e_cnt:"+e_cnt);

		vo.setP_no(p_no);
		vo.setE_cnt(e_cnt);
		
		int result = DAO.pexport(vo);
		
		if(result == 0) {
			System.out.println("물품출고 실패");
			doGet(request, response);
		}
		if(result !=0 ) {
			DAO.updateI_cntExp(vo);
			System.out.println("물품출고 성공");
			response.sendRedirect("export");
		}
		
		
	}

}