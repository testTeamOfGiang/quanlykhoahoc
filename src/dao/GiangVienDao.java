package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBC_Connection;
import entity.Giangvien;
import entity.LopHoc;
import mapper.GiangVien_Mapper;
import mapper.LopHoc_Mapper;
import utils.PageRegulation;

public class GiangVienDao {

	public void add(Giangvien gv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into GIANGVIEN(ten_GV,ngaysinh_GV,sodt_GV,diachi_GV,ghichu_GV)" + "values(?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, gv.getTen_GV());
		preparedStatement.setDate(2, gv.getNgaysinh_GV());
		preparedStatement.setString(3, gv.getSodt_GV());
		preparedStatement.setString(4, gv.getDiachi_GV());
		preparedStatement.setString(5, gv.getGhichu_GV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void update(Giangvien gv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update GIANGVIEN set ten_GV=?,ngaysinh_GV=?,sodt_GV=?,diachi_GV=?,ghichu_GV=? where id_GV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, gv.getTen_GV());
		preparedStatement.setDate(2, gv.getNgaysinh_GV());
		preparedStatement.setString(3, gv.getSodt_GV());
		preparedStatement.setString(4, gv.getDiachi_GV());
		preparedStatement.setString(5, gv.getGhichu_GV());
		preparedStatement.setInt(6, gv.getId_GV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public void delete(Giangvien gv) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "delete from GIANGVIEN where id_GV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, gv.getId_GV());
		preparedStatement.executeUpdate();
		con.close();
	}

	public List<Giangvien> getPage(int page) throws SQLException {
		ArrayList<Giangvien> list = new ArrayList<Giangvien>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from( select *,ROW_NUMBER() over (order by id_GV) as "
				+ "rownum from GIANGVIEN) as gv where gv.rownum BETWEEN ? and ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		page = page < 0 ? 0 : page;
		int endPage = page < 0 ? 1 : page + 1;
		preparedStatement.setInt(1, page * PageRegulation.LINES_PER_PAGE + 1);
		preparedStatement.setInt(2, endPage * PageRegulation.LINES_PER_PAGE);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Giangvien gv = new GiangVien_Mapper().map(resultSet);
			list.add(gv);
		}
		con.close();
		return list;
	}

	public List<Giangvien> find(String name) throws SQLException {
		ArrayList<Giangvien> list = new ArrayList<Giangvien>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from GIANGVIEN where ten_GV like concat('%',?,'%')";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Giangvien gv = new GiangVien_Mapper().map(resultSet);
			list.add(gv);
		}
		con.close();
		return list;
	}

	public Giangvien findByID(int id) throws SQLException {
		Giangvien gv = null;
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from GIANGVIEN where id_GV = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			gv = new GiangVien_Mapper().map(resultSet);
		}
		con.close();
		return gv;
	}

	public List<LopHoc> getLopHoc(Giangvien gv) throws SQLException {
		ArrayList<LopHoc> lop = new ArrayList<LopHoc>();
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from LOPHOC as lh where lh.id_GV=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, gv.getId_GV());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LopHoc lopHoc = new LopHoc_Mapper().map(resultSet);
			lop.add(lopHoc);
		}
		con.close();
		return lop;
	}

}
