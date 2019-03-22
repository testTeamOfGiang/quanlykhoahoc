package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.JDBC_Connection;
import entity.HocVien_LopHoc;

public class HocVien_LopHocDAO {

	public void addHocVien_LopHoc(HocVien_LopHoc hvlh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN_LOPHOC values(?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, hvlh.getId_HV());
		preparedStatement.setInt(2, hvlh.getId_LH());
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

}
