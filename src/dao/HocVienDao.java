package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.Hocvien;
import mapper.HocVien_Mapper;

public class HocVienDao {

	public void add(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN(ten_HV,sodt_HV,diachi_HV) values(?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, hv.getTen_HV());
		preparedStatement.setString(2, hv.getSodt_HV());
		preparedStatement.setString(3, hv.getDiachi_HV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void update(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update HOCVIEN set ten_HV=?,sodt_HV=?,diachi_HV=? where id_HV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, hv.getTen_HV());
		preparedStatement.setString(2, hv.getSodt_HV());
		preparedStatement.setString(3, hv.getDiachi_HV());
		preparedStatement.setInt(4, hv.getId_HV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void delete(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from HOCVIEN where id_HV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, hv.getId_HV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public List<Hocvien> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		ArrayList<Hocvien> hocViens = new ArrayList<Hocvien>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_HV) as "
				+ "rownum from HOCVIEN) as hv where hv.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Hocvien hv = new HocVien_Mapper().map(resultSet);
			hocViens.add(hv);
		}
		con.close();
		return hocViens;
	}

	public List<Hocvien> find(String key) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		ArrayList<Hocvien> hocViens = new ArrayList<Hocvien>();
		String sql = "select * from HOCVIEN where ten_HV like concat('%',?,'%')";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, key);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Hocvien hv = new HocVien_Mapper().map(resultSet);
			hocViens.add(hv);
		}
		con.close();
		return hocViens;
	}

	public Hocvien findById(int id) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		Hocvien hv = null;
		String sql = "select * from HOCVIEN where id_HV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			hv = new HocVien_Mapper().map(resultSet);
		}
		con.close();
		return hv;
	}
}
