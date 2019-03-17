package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.LopHoc;
import mapper.LopHoc_Mapper;

// Chưa xong

public class LopHocDAO {
	
	/**
	 * Thêm một lớp vào bảng LOPHOC
	 * @param lh đối tượng LopHoc
	 * @throws SQLException 
	 */
	public void addLopHoc(LopHoc lh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into LOPHOC "
				+ "(id_KH, ngaybatdau, ngayketthuc, id_GV, ten_LH, id_PH, ghichu_LH) "
				+ "values(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lh.getId_KH());
		preparedStatement.setDate(2, lh.getNgaybatdau());
		preparedStatement.setDate(3, lh.getNgayketthuc());
		preparedStatement.setInt(4, lh.getId_GV());
		preparedStatement.setString(5, lh.getTen_LH());
		preparedStatement.setInt(6, lh.getId_PH());
		preparedStatement.setString(7, lh.getGhichu_LH());
		preparedStatement.executeUpdate();
		con.close();
	}
	
	/**
	 * Cập nhật thông tin lớp học
	 * Yêu cầu lớp học phải đầy đủ thông tin.
	 * @param lh đối tượng LopHoc
	 * @throws SQLException
	 */
	public void updateLopHoc(LopHoc lh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update LOPHOC "
				+ "set id_KH = ?, "
				+ "set ngaybatdau = ?, "
				+ "set ngayketthuc = ?, "
				+ "set id_GV = ?, "
				+ "set ten_LH = ?, "
				+ "set id_PH = ?,"
				+ "set ghichu_LH = ?"
				+ "where id_LH = ?";
		PreparedStatement preStatement = con.prepareStatement(sql);
		preStatement.setInt(1, lh.getId_KH());
		preStatement.setDate(2, lh.getNgaybatdau());
		preStatement.setDate(3, lh.getNgayketthuc());
		preStatement.setInt(4, lh.getId_GV());
		preStatement.setString(5, lh.getTen_LH());
		preStatement.setInt(6, lh.getId_PH());
		preStatement.setString(7, lh.getGhichu_LH());
		preStatement.executeUpdate();
		con.close();
	}
	
	// Chức năng này chưa xong
	/**
	 * Chức năng này chưa xong
	 * Xoá 1 lớp học khỏi bảng Lớp học.
	 * Xoá cả lịch học
	 * Xoá ..
	 * @param lh
	 * @throws SQLException
	 */
	public void deleteLopHoc(LopHoc lh) throws SQLException {
		
		System.out.println("Chức năng xoá lớp chưa xong. Sử dụng có thể gây lỗi");
		
		new LichHocDAO().deleteLichHocByLop(lh);
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lh.getId_LH());
		preparedStatement.executeUpdate();
		con.close();
	}
	
	/**
	 * Tìm Lớp by ID lớp
	 * @param id_LH
	 * @return
	 * @throws SQLException
	 */
	LopHoc findById_LH(int id_LH) throws SQLException{
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		LopHoc lh = new LopHoc_Mapper().map(resultSet);
		con.close();
		return lh;
	}
	
	/**
	 * Tìm lớp by ID Khoá học
	 * 
	 * @param id_KH
	 * @return
	 * @throws SQLException
	 */
	public List<LopHoc> findById_KH(int id_KH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where id_KH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_KH);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	/**
	 * Tìm lớp theo id giáo viên
	 * 
	 * @param id_GV
	 * @return List<LopHoc>
	 * @throws SQLException
	 */
	public List<LopHoc> findById_GV(int id_GV) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where id_GV = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_GV);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	/**
	 * Tìm lớp theo id Phòng
	 * @param id_PH
	 * @return List<LopHoc>
	 * @throws SQLException
	 */
	public List<LopHoc> findById_PH(int id_PH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where id_PH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_PH);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	/**
	 * Tìm lớp theo tên
	 * 
	 * @param ten_LH
	 * @return List<LopHoc>
	 * @throws SQLException
	 */
	public List<LopHoc> findByName(String ten_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where ten_LH like concat('%',?,'%') order by id_LH DESC";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, ten_LH);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	/**
	 * Tìm lớp in ghi chú.
	 * Sắp xếp giảm dần theo id
	 * 
	 * @param ghiChu
	 * @return
	 * @throws SQLException
	 */
	public List<LopHoc> findByGhiChu(String ghiChu) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where ghichu_LH like concat('%',?,'%') order by id_LH DESC";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, ghiChu);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	/**
	 * Tìm xem có lớp nào học trong khoảng thời gian này 
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<LopHoc> findInPeriod(Date date) throws SQLException{
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where ? between ngaybatdau and ngayketthuc";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setDate(1, date);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		while(resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
	
	public List<LopHoc> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_LH DESC) as "
				+ "rownum from LOPHOC) as lh where lh.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}
}
