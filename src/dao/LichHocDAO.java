package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.LichHoc;
import mapper.LichHoc_Mapper;

public class LichHocDAO {

	/**
	 * Thêm lịch học cho lớp. vd: thứ Tue, tiết 1, 2, 3, 4, 5
	 * 
	 * @param lih
	 * @throws SQLException
	 */
	public void addLichHoc(LichHoc lih) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into LICHHOC(id_LH, thu, tiet, ghichu_LIH) values(?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lih.getId_LH());
		preparedStatement.setInt(2, lih.getThu());
		preparedStatement.setString(3, lih.getTiet());
		preparedStatement.setString(4, lih.getGhichu_LIH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * cập nhật đưa đầy đủ thông tin vào k là ăn lol
	 * 
	 * @param lih đưa đầy đủ thông tin vào
	 * @throws SQLException
	 */
	public void updateLichHoc(LichHoc lih) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update LICHHOC " + "set id_LH = ?, " + "set thu = ?, " + "set tiet = ?, " + "set ghichu_LIH = ? "
				+ "where id_LIH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lih.getId_LH());
		preparedStatement.setInt(2, lih.getThu());
		preparedStatement.setString(3, lih.getTiet());
		preparedStatement.setString(4, lih.getGhichu_LIH());
		preparedStatement.setInt(5, lih.getId_LIH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá by id_LIH.
	 * Không xoá lớp
	 * 
	 * @param lih
	 * @throws SQLException
	 */
	public void deleteLichHoc(LichHoc lih) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LICHHOC where id_LIH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lih.getId_LIH());
		preparedStatement.execute();
		con.close();
	}


	public void deleteLichHocById_LH_Thu(int id_LH, int thu) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LICHHOC where id_LH = ? and thu = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		preparedStatement.setInt(2, thu);
		preparedStatement.execute();
		con.close();
	}
	
	public void deleteLichHocById_LH(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from LICHHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		preparedStatement.execute();
		con.close();
	}

	/**
	 * Tìm thứ và tiết theo lớp học
	 * 
	 * @param lh lớp học
	 * @return List<LichHoc> list lịch học
	 * @throws SQLException
	 */
	public ArrayList<LichHoc> findById_LH(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LICHHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		
		ArrayList<LichHoc> lstLIH = new ArrayList<LichHoc>();
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			LichHoc lih = new LichHoc_Mapper().map(result);
			lstLIH.add(lih);
		}
		con.close();
		return lstLIH;
	}

	/**
	 * Tìm tiết theo lớp học và thứ mà lớp đó học
	 * 
	 * @param lh lớp học
	 * @param thu thứ
	 * @return List<LichHoc> list lịch học
	 * @throws SQLException
	 */
	public ArrayList<LichHoc> findById_LH_Thu(int id_LH, int thu) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LICHHOC where id_LH = ? and thu = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		preparedStatement.setInt(2, thu);
		ArrayList<LichHoc> lstLIH = new ArrayList<LichHoc>();
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			LichHoc lih = new LichHoc_Mapper().map(result);
			lstLIH.add(lih);
		}
		con.close();
		return lstLIH;
	}
	
	/**
	 * Tìm lịch by ID_LIH
	 * 
	 * @param id_LIH
	 * @return
	 * @throws SQLException
	 */
	public LichHoc findById(int id_LIH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LICHHOC where id_LIH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LIH);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		LichHoc lih = new LichHoc_Mapper().map(resultSet);
		con.close();
		return lih;
	}

	/**
	 * Tìm lịch by thứ. Viết chuẩn hoa thường
	 * 
	 * @param thu thuộc Mon, Tue, Wed, Thu, Fri, Sat, Sun
	 * @return
	 * @throws SQLException
	 */
	public List<LichHoc> findByThu(String thu) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LICHHOC where thu like concat('%',?,'%') order by id_LH DESC";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, thu);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LichHoc> lstLIH = new ArrayList<LichHoc>();
		while (resultSet.next()) {
			LichHoc lih = new LichHoc_Mapper().map(resultSet);
			lstLIH.add(lih);
		}
		con.close();
		return lstLIH;
	}

	/**
	 * Tìm theo 1 tiết để đạt hiệu quả cao nhất
	 * 
	 * @param thu thuộc Mon, Tue, Wed, Thu, Fri, Sat, Sun
	 * @return
	 * @throws SQLException
	 */
	public List<LichHoc> findByTiet(String tiet) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LICHHOC where tiet like concat('%',?,'%') order by id_LH DESC";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, tiet);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<LichHoc> lstLIH = new ArrayList<LichHoc>();
		while (resultSet.next()) {
			LichHoc lih = new LichHoc_Mapper().map(resultSet);
			lstLIH.add(lih);
		}
		con.close();
		return lstLIH;
	}

	/**
	 * 
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<LichHoc> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<LichHoc> lstLIH = new ArrayList<LichHoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_LH DESC) as "
				+ "rownum from LICHHOC) as lih where lih.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LichHoc lih = new LichHoc_Mapper().map(resultSet);
			lstLIH.add(lih);
		}
		con.close();
		return lstLIH;
	}

}
