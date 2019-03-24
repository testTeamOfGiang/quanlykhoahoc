package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.HocVien_LopHoc;
import mapper.HocVien_LopHoc_Mapper;

public class HocVien_LopHocDAO {

	/**
	 * Cho phép thêm nhưng k cho nhập điểm
	 * 
	 * @param hvlh gồm mã lớp, mã học viên, ghi chú
	 * @throws SQLException
	 */
	public void addHocVien_LopHoc(HocVien_LopHoc hvlh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN_LOPHOC values(?,?,-1,-1,-1,-1,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, hvlh.getId_HV());
		preparedStatement.setInt(2, hvlh.getId_LH());
		preparedStatement.setString(3, hvlh.getGhichu_HVLH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Thêm học viên vào lớp
	 * 
	 * @param id_HV
	 * @param id_LH
	 * @throws SQLException
	 */
	public void addHocVien_LopHoc(int id_HV, int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN_LOPHOC values(?,?,-1,-1,-1,-1,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_HV);
		preparedStatement.setInt(2, id_LH);
		preparedStatement.setString(3, "");
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá khi có cả mã Lớp và mã Học viên
	 * 
	 * @param hvlh
	 * @throws SQLException
	 */
	public void deleteHocVien_LopHoc(HocVien_LopHoc hvlh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from HOCVIEN_LOPHOC where id_HV = ? and id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, hvlh.getId_HV());
		preparedStatement.setInt(2, hvlh.getId_LH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá học viên khỏi lớp
	 * 
	 * @param id_HV
	 * @param id_LH
	 * @throws SQLException
	 */
	public void deleteHocVien_LopHoc(int id_HV, int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from HOCVIEN_LOPHOC where id_HV = ? and id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_HV);
		preparedStatement.setInt(2, id_LH);
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Xoá theo mã Lớp
	 * 
	 * @param id_LH
	 * @throws SQLException
	 */
	public void deleteHocVien_LopHocById_LH(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from HOCVIEN_LOPHOC where id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		preparedStatement.executeUpdate();
		con.close();
	}

	public void updateHocVien_LopHoc(HocVien_LopHoc hvlh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update HOCVIEN_LOPHOC set " + " diem_1 = ?, " + " diem_2 = ?, " + " diem_3 = ?, "
				+ " diem_4 = ?, " + " ghichu_HVLH = ? " + " where id_HV = ? and id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setFloat(1, hvlh.getDiem_1());
		preparedStatement.setFloat(2, hvlh.getDiem_2());
		preparedStatement.setFloat(3, hvlh.getDiem_3());
		preparedStatement.setFloat(4, hvlh.getDiem_4());
		preparedStatement.setString(5, hvlh.getGhichu_HVLH());
		preparedStatement.setInt(6, hvlh.getId_HV());
		preparedStatement.setInt(7, hvlh.getId_LH());
		preparedStatement.executeUpdate();
		con.close();
	}

	/**
	 * Tính sĩ số của một lớp
	 * 
	 * @param id_LH
	 * @return
	 * @throws SQLException
	 */
	public int getSiSoById_LH(int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select count(*) as 'dem' from HOCVIEN_LOPHOC where id_LH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int dem = resultSet.getInt("dem");
		con.close();
		return dem;
	}

	public boolean isExists(int id_HV, int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select count(*) as 'dem' from HOCVIEN_LOPHOC where id_HV=? and id_LH = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_HV);
		preparedStatement.setInt(2, id_LH);
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean check = false;
		while (resultSet.next()) {
			if (resultSet.getInt("dem") > 0)
				check = true;
		}
		con.close();
		return check;
	}

	public List<HocVien_LopHoc> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<HocVien_LopHoc> lstHV_LH = new ArrayList<HocVien_LopHoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_LH DESC) as "
				+ "rownum from HOCVIEN_LOPHOC) as hvlh where hvlh.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			HocVien_LopHoc lh = new HocVien_LopHoc_Mapper().map(resultSet);
			lstHV_LH.add(lh);
		}
		con.close();
		return lstHV_LH;
	}

	/**
	 * Get page theo lớp
	 * 
	 * @param id_LH
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<HocVien_LopHoc> getPageByID_LH(int id_LH, int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<HocVien_LopHoc> lstHV_LH = new ArrayList<HocVien_LopHoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_HV ASC) as "
				+ "rownum from HOCVIEN_LOPHOC where id_LH = ?) as hvlh where hvlh.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_LH);
		preparedStatement.setInt(2, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			HocVien_LopHoc lh = new HocVien_LopHoc_Mapper().map(resultSet);
			lstHV_LH.add(lh);
		}
		con.close();
		return lstHV_LH;
	}
}
