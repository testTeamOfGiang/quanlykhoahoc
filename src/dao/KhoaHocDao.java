package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.Khoahoc;
import entity.Lophoc;
import mapper.KhoaHoc_Mapper;
import mapper.LopHoc_Mapper;

public class KhoaHocDao {

	public void add(Khoahoc kh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into KHOAHOC(ten_KH,gia_KH,ghichu_KH) values(?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, kh.getTen_KH());
		preparedStatement.setInt(2, kh.getGia_KH());
		preparedStatement.setString(3, kh.getGhichu_KH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void update(Khoahoc kh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update KHOAHOC set ten_KH=?,gia_KH=?,ghichu_KH=? where id_KH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, kh.getTen_KH());
		preparedStatement.setInt(2, kh.getGia_KH());
		preparedStatement.setString(3, kh.getGhichu_KH());
		preparedStatement.setInt(4, kh.getId_KH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void delete(Khoahoc kh) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from KHOAHOC where id_KH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, kh.getId_KH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public List<Khoahoc> getPage(int page) throws SQLException {
		List<Khoahoc> list = new ArrayList<Khoahoc>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_KH) as "
				+ "rownum from KHOAHOC) as kh where kh.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Khoahoc kh = new KhoaHoc_Mapper().map(resultSet);
			list.add(kh);
		}
		con.close();
		return list;
	}

	public List<Khoahoc> find(String key) throws SQLException {
		List<Khoahoc> list = new ArrayList<Khoahoc>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from KHOAHOC where ten_KH like concat('%',?,'%')";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, key);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Khoahoc kh = new KhoaHoc_Mapper().map(resultSet);
			list.add(kh);
		}
		con.close();
		return list;
	}

	public Khoahoc findById(int id) throws SQLException {
		Khoahoc kh = null;
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from KHOAHOC where id_KH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			kh = new KhoaHoc_Mapper().map(resultSet);
		}
		con.close();
		return kh;
	}

	public List<Lophoc> getLop(Khoahoc kh) throws SQLException {
		List<Lophoc> list = new ArrayList<Lophoc>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC as lh inner join KHOAHOC as kh on lh.id_KH=kh.id_KH where kh.id_KH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, kh.getId_KH());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Lophoc lh = new LopHoc_Mapper().map(resultSet);
			list.add(lh);
		}
		con.close();
		return list;
	}
}
