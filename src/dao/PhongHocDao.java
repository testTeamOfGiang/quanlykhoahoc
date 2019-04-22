package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.LopHoc;
import entity.Phonghoc;
import mapper.LopHoc_Mapper;
import mapper.PhongHoc_Mapper;

public class PhongHocDao {

	public void add(Phonghoc ph) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into PHONGHOC(ten_PH,ghichu_PH, succhua_PH) values(?,?, ?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, ph.getTen_PH());
		preparedStatement.setString(2, ph.getGhichu_PH());
		preparedStatement.setInt(3, ph.getSucChua_PH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void delete(Phonghoc ph) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from PHONGHOC where id_PH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, ph.getId_PH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void update(Phonghoc ph) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update PHONGHOC set ten_PH=?,ghichu_PH=?, succhua_PH=? where id_PH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, ph.getTen_PH());
		preparedStatement.setString(2, ph.getGhichu_PH());
		preparedStatement.setInt(3, ph.getSucChua_PH());
		preparedStatement.setInt(4, ph.getId_PH());
		preparedStatement.executeUpdate();
		con.close();
	}

	public List<LopHoc> getLop(Phonghoc ph) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<LopHoc> list = new ArrayList<LopHoc>();
		String sql = "select * from LOPHOC as lh inner join PHONGHOC as ph on lh.id_PH=ph.id_PH where ph.id_PH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, ph.getId_PH());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			list.add(lh);
		}
		con.close();
		return list;
	}

	public List<Phonghoc> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<Phonghoc> list = new ArrayList<Phonghoc>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_PH) as "
				+ "rownum from PHONGHOC) as ph where ph.rownum BETWEEN ? and 50";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, page);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Phonghoc ph = new PhongHoc_Mapper().map(resultSet);
			list.add(ph);
		}
		con.close();
		return list;
	}

	public List<Phonghoc> find(String key) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<Phonghoc> list = new ArrayList<Phonghoc>();
		String sql = "select * from PHONGHOC where ten_PH like concat('%',?,'%')";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, key);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Phonghoc ph = new PhongHoc_Mapper().map(resultSet);
			list.add(ph);
		}
		con.close();
		return list;
	}

	public Phonghoc findById(int id) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		Phonghoc ph = null;
		String sql = "select * from PHONGHOC where id_PH =?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			ph = new PhongHoc_Mapper().map(resultSet);
		}
		con.close();
		return ph;
	}
	
}
