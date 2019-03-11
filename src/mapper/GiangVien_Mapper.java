package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Giangvien;

public class GiangVien_Mapper implements Mapper<Giangvien> {

	@Override
	public Giangvien map(ResultSet resultSet) throws SQLException {
		Giangvien gv = new Giangvien();
		gv.setId_GV(resultSet.getInt("id_GV"));
		gv.setTen_GV(resultSet.getString("ten_GV"));
		gv.setNgaysinh_GV(resultSet.getDate("ngaysinh_GV"));
		gv.setSodt_GV(resultSet.getString("sodt_GV"));
		gv.setDiachi_GV(resultSet.getString("diachi_GV"));
		gv.setGhichu_GV(resultSet.getString("ghichu_GV"));
		return gv;
	}

}
