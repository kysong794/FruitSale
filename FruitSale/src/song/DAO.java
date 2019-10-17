package song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	public static Connection getConnection () throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","fruit","hkit2019");
		System.out.println("DB연결");
		return con;
	}
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//물품등록
	public static int reg(fruitVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into i_product (p_no,p_name) "
					+" VALUES ((SELECT nvl(max(p_no),0)+1 from i_product),?)";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getP_name());
			result=ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//물품 리스트
	public static List<fruitVo>i_productlist() {
		List<fruitVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select p_no,p_name,p_cnt "
					+" from i_product ";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fruitVo vo = new fruitVo();
				vo.setP_no(rs.getString("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_cnt(rs.getString("p_cnt"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}	
		return list;
	}
	//물품 입고 
	public static int pimport(fruitVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into i_import (i_no,p_no,i_cnt) "
					+" VALUES ((select nvl(max(i_no),0)+1 from i_import),?,?)";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getP_no());
			ps.setString(2, vo.getI_cnt());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//물품 출고
	public static int pexport(fruitVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into i_export (e_no,p_no,e_cnt) "
					+" VALUES ((select nvl(max(e_no),0)+1 from i_export),?,?) ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getP_no());
			ps.setString(2, vo.getE_cnt());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//i_product 테이블에 수량 조정해줄수있는 메소드 2개 입고용 출고용 만들기 리턴은 필요없음
	//입고가 있을시 i_produt에 수량이 같이 입고되게 만드는 메소드
	public static void updateI_cntImp(fruitVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update i_product "
					+" set p_cnt = p_cnt + ?"
					+" where p_no = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getI_cnt());
			ps.setString(2, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		
	}
	//출고가 있을시 i_produt에 수량이 같이 출고되게 만드는 메소드
	public static void updateI_cntExp(fruitVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update i_product "
					+" set p_cnt = p_cnt - ? "
					+" where p_no = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getE_cnt());
			ps.setString(2, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
	}
	
	//물품 조회
	public static List<fruitVo> productlist (){
		List<fruitVo> productlist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select p_no,p_name,p_cnt,to_char(p_regdate,'yyyy-mm-dd') as p_regdate "
					+" from i_product "
					+" order by p_no desc ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fruitVo vo = new fruitVo();
				vo.setP_no(rs.getString("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_cnt(rs.getString("p_cnt"));
				vo.setP_regdate(rs.getString("p_regdate"));
				productlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return productlist;
	}
	//입고 물품 조회
	public static List<fruitVo> importlist (){
		List<fruitVo> importlist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select b.i_no,a.p_name,b.i_cnt,to_char(b.i_date,'yyyy-mm-dd') as i_date "
					+" from i_product a "
					+" inner join i_import b on a.p_no = b.p_no "
					+" order by b.i_no DESC ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fruitVo vo = new fruitVo();
				vo.setI_no(rs.getString("i_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setI_cnt(rs.getString("i_cnt"));
				vo.setI_date(rs.getString("i_date"));
				importlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return importlist;
	}
	//출고 물품 조회
	public static List<fruitVo> exportlist (){
		List<fruitVo> exportlist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select b.e_no,a.p_name,b.e_cnt,to_char(b.e_date,'yyyy-mm-dd') as e_date "
					+" from i_product a "
					+" inner join i_export b on a.p_no = b.p_no "
					+" order by b.e_no DESC ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fruitVo vo = new fruitVo();
				vo.setE_no(rs.getString("e_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setE_cnt(rs.getString("e_cnt"));
				vo.setE_date(rs.getString("e_date"));
				exportlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return exportlist;
	}
	
	//물품 리스트 파라미터 p_no값을 받아야함
	public static List<fruitVo> productlist (String p_no){
		List<fruitVo> productlist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select p_no,p_name,p_cnt,to_char(p_regdate,'yyyy-mm-dd') as p_regdate "
					+" from i_product "
					+" where p_no = ? "
					+" order by p_no desc ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, p_no);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				fruitVo vo = new fruitVo();
				vo.setP_no(rs.getString("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_cnt(rs.getString("p_cnt"));
				vo.setP_regdate(rs.getString("p_regdate"));
				productlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return productlist;
	}
	//물품 정보 수정
	public static int mod (fruitVo vo){
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update i_product "
					+" set p_name = ? "
					+" where p_no = ? ";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getP_name());
			ps.setString(2, vo.getP_no());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}	
		return result;
	}
}
