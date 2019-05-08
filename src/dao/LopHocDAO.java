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
import utils.PageRegulation;

// Chưa xong

public class LopHocDAO {

	/**
	 * Thêm một lớp vào bảng LOPHOC
	 * 
	 * @param lh đối tượng LopHoc
	 * @throws SQLException
	 */
	public void addLopHoc(LopHoc lh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into LOPHOC "
				+ "(id_KH, ngaybatdau, ngayketthuc, id_GV, ten_LH, id_PH, ghichu_LH, siso_LH) "
				+ "values(?,?,?,?,?,?,?,0)"; // 0 cuối là sĩ số. đã cài default. cứ để đây lúc edit đỡ bị lẫn
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
	 * Cập nhật thông tin lớp học Yêu cầu lớp học phải đầy đủ thông tin.
	 * 
	 * @param lh đối tượng LopHoc
	 * @throws SQLException
	 */
	public void updateLopHoc(LopHoc lh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = " update LOPHOC " + " set id_KH = ?, " + " ngaybatdau = ?, " + " ngayketthuc = ?, "
				+ " id_GV = ?, " + " ten_LH = ?, " + " id_PH = ?, " + " ghichu_LH = ?, " + " siso_LH = ?"
				+ " where id_LH = ?";
		PreparedStatement preStatement = con.prepareStatement(sql);
		preStatement.setInt(1, lh.getId_KH());
		preStatement.setDate(2, lh.getNgaybatdau());
		preStatement.setDate(3, lh.getNgayketthuc());
		preStatement.setInt(4, lh.getId_GV());
		preStatement.setString(5, lh.getTen_LH());
		preStatement.setInt(6, lh.getId_PH());
		preStatement.setString(7, lh.getGhichu_LH());
		int siso_LH = new HocVien_LopHocDAO().getSiSoById_LH(lh.getId_LH());
		preStatement.setInt(8, siso_LH);
		preStatement.setInt(9, lh.getId_LH());
		preStatement.executeUpdate();
		con.close();
	}

	/**
	 * Chức năng này chưa xong Xoá 1 lớp học khỏi bảng Lớp học. Xoá cả lịch học Xoá
	 * ..
	 * 
	 * @param lh
	 * @throws SQLException
	 */
	public void deleteLopHoc(LopHoc lh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		new LichHocDAO().deleteLichHocById_LH(lh.getId_LH());
		new HocVien_LopHocDAO().deleteHocVien_LopHocById_LH(lh.getId_LH());

		String sql = "delete from LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lh.getId_LH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá tất cả lớp thông qua id_GV
	 * 
	 * @param id_GV
	 * @throws SQLException
	 */
	public void deleteLopHocById_GV(int id_GV) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LOPHOC where id_GV = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_GV);
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá tất cả lớp thông qua id_KH
	 * 
	 * @param id_KH
	 * @throws SQLException
	 */
	public void deleteLopHocById_KH(int id_KH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LOPHOC where id_KH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_KH);
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá tất cả lớp thông qua id_PH
	 * 
	 * @param id_PH
	 * @throws SQLException
	 */
	public void deleteLopHocById_PH(int id_PH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LOPHOC where id_PH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_PH);
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Chuyển sang phòng học khác
	 * 
	 * @param lh    lớp học
	 * @param id_PH phòng mới
	 * @throws SQLException
	 */
	public void chuyenPhongHoc(LopHoc lh, int id_PH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update LOPHOC set id_PH = ? where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_PH);
		preparedStatement.setInt(2, lh.getId_LH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Kiểm tra lớp có tồn tại hay k
	 * 
	 * @param id_LH
	 * @return true nếu tồn tại. false ngược lại
	 * @throws SQLException
	 */
	public boolean isExist(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select count(*) as 'dem' from LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int dem = resultSet.getInt("dem");
		con.close();

		return dem > 0;
	}

	/**
	 * Tìm Lớp by ID lớp
	 * 
	 * @param id_LH
	 * @return
	 * @throws SQLException
	 */
	public LopHoc findById_LH(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		ResultSet resultSet = preparedStatement.executeQuery();
		LopHoc lh = null;
		while (resultSet.next())
			lh = new LopHoc_Mapper().map(resultSet);
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
	 * 
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
		String sql = "select * from fn_findLopByName(?) order by id_LH DESC";
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
	 * Tìm lớp in ghi chú. Sắp xếp giảm dần theo id
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
	public List<LopHoc> findInPeriod(Date date) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC where ? between ngaybatdau and ngayketthuc";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setDate(1, date);
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
	 * 
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<LopHoc> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<LopHoc> lstLH = new ArrayList<LopHoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_LH DESC) as "
				+ "rownum from LOPHOC) as lh where lh.rownum BETWEEN ? and ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		
		int lineOfPage = PageRegulation.LINES_PER_PAGE;
		page = page < 0 ? 1 : page;
		preparedStatement.setInt(1, page * lineOfPage + 1);
		int endPage = page <= 0 ? 1 : page + 1;
		preparedStatement.setInt(2, endPage * lineOfPage);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			lstLH.add(lh);
		}
		con.close();
		return lstLH;
	}

//-------------------------- EXTRA ---------------------------//

	/**
	 * 
	 * @param id_KH
	 * @param id_PH
	 * @param id_GV
	 * @return String[0] = tên Khoá học, String[1] = tên phòng học, String[2] = tên giảng viên
	 * @throws SQLException
	 */
	public String[] getTenKH_PH_GV(int id_KH, int id_PH, int id_GV) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select ten_KH, ten_PH, ten_GV from KHOAHOC, PHONGHOC, GIANGVIEN where id_KH = ? and id_PH=? and id_GV = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_KH);
		preparedStatement.setInt(2, id_PH);
		preparedStatement.setInt(3, id_GV);
		ResultSet resultSet = preparedStatement.executeQuery();
		String ten_KH = null;
		String ten_PH = null;
		String ten_GV = null;
		while (resultSet.next()) {
			ten_KH = resultSet.getString("ten_KH");
			ten_PH = resultSet.getString("ten_PH");
			ten_GV = resultSet.getString("ten_GV");
		}
		con.close();
		String result[] = { ten_KH, ten_PH , ten_GV};
		return result;
	}
}
