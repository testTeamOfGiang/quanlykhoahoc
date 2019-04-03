package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Hocvien;

public class HocVien_Mapper implements Mapper<Hocvien> {

	@Override
	public Hocvien map(ResultSet resultSet) throws SQLException {
		Hocvien hv = new Hocvien();
		hv.setId_HV(resultSet.getInt("id_HV"));
		hv.setTen_HV(resultSet.getString("ten_HV"));
		hv.setSodt_HV(resultSet.getString("sodt_HV"));
		hv.setDiachi_HV(resultSet.getString("diachi_HV"));
		hv.setNgaysinh_HV(resultSet.getDate("ngaysinh_HV"));
		return hv;
	}

}
