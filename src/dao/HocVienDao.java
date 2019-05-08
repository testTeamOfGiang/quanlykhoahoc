package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.Hocvien;
import entity.LopHoc;
import mapper.HocVien_Mapper;
import mapper.LopHoc_Mapper;
import utils.PageRegulation;

public class HocVienDao {

	public void add(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN(ten_HV,sodt_HV, ngaysinh_HV,diachi_HV) values(?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, hv.getTen_HV());
		preparedStatement.setString(2, hv.getSodt_HV());
		preparedStatement.setDate(3, hv.getNgaysinh_HV());
		preparedStatement.setString(4, hv.getDiachi_HV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void update(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update HOCVIEN set ten_HV=?,sodt_HV=?,ngaysinh_HV = ?,diachi_HV=? where id_HV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, hv.getTen_HV());
		preparedStatement.setString(2, hv.getSodt_HV());
		preparedStatement.setDate(3, hv.getNgaysinh_HV());
		preparedStatement.setString(4, hv.getDiachi_HV());
		preparedStatement.setInt(5, hv.getId_HV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void delete(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		con.setAutoCommit(false);
		String sql1 = "delete from HOCVIEN_LOPHOC where id_HV=?";
		String sql2 = "delete from HOCVIEN where id_HV=?";
		PreparedStatement preparedStatement1 = con.prepareStatement(sql2);
		preparedStatement1.setInt(1, hv.getId_HV());
		PreparedStatement preparedStatement2 = con.prepareStatement(sql1);
		preparedStatement2.setInt(1, hv.getId_HV());
		preparedStatement2.executeUpdate();
		preparedStatement1.executeUpdate();
		con.commit();
		con.close();
	}

	public List<Hocvien> getPage(int page) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		ArrayList<Hocvien> hocViens = new ArrayList<Hocvien>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_HV) as "
				+ "rownum from HOCVIEN) as hv where hv.rownum BETWEEN ? and ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		page = page < 0 ? 0 : page;
		int endpage = page < 0 ? 1 : page + 1;
		preparedStatement.setInt(1, page * PageRegulation.PAGE_LIMIT_HOCVIEN + 1);
		preparedStatement.setInt(2, endpage*PageRegulation.PAGE_LIMIT_HOCVIEN);
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

	public int addLop(int id_HV, int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into HOCVIEN_LOPHOC(id_HV,id_LH,diem_1,diem_2,diem_3,diem_4) values(?,?,-1,-1,-1,-1)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_HV);
		preparedStatement.setInt(2, id_LH);
		int result = preparedStatement.executeUpdate();
		con.close();
		return result;
	}

	public int deleteLop(int id_HV, int id_LH) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from HOCVIEN_LOPHOC where id_HV=? and id_LH=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id_HV);
		preparedStatement.setInt(2, id_LH);
		int result = preparedStatement.executeUpdate();
		con.close();
		return result;
	}

	public List<LopHoc> getLopHoc(Hocvien hv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		List<LopHoc> list = new ArrayList<LopHoc>();
		String sql = "select LOPHOC.* from LOPHOC inner join HOCVIEN_LOPHOC "
				+ "on LOPHOC.id_LH=HOCVIEN_LOPHOC.id_LH where HOCVIEN_LOPHOC.id_HV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, hv.getId_HV());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LopHoc lh = new LopHoc_Mapper().map(resultSet);
			list.add(lh);
		}
		return list;
	}
}
