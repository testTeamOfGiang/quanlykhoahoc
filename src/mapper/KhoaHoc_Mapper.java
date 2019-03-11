package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Khoahoc;

public class KhoaHoc_Mapper implements Mapper<Khoahoc> {

	@Override
	public Khoahoc map(ResultSet resultSet) throws SQLException  {
		Khoahoc kh = new Khoahoc();
		kh.setId_KH(resultSet.getInt("id_KH"));
		kh.setTen_KH(resultSet.getString("ten_KH"));
		kh.setGia_KH(resultSet.getInt("gia_KH"));
		kh.setGhichu_KH(resultSet.getString("ghichu_KH"));
		return kh;
	}

}
