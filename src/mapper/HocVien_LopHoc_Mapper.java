package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.HocVien_LopHoc;

public class HocVien_LopHoc_Mapper implements Mapper<HocVien_LopHoc> {

	@Override
	public HocVien_LopHoc map(ResultSet resultSet) throws SQLException {
		HocVien_LopHoc hvlh = new HocVien_LopHoc();
		hvlh.setId_HV(resultSet.getInt("id_HV"));
		hvlh.setId_LH(resultSet.getInt("id_LH"));
		hvlh.setDiem_1(resultSet.getFloat("diem_1"));
		hvlh.setDiem_2(resultSet.getFloat("diem_2"));
		hvlh.setDiem_3(resultSet.getFloat("diem_3"));
		hvlh.setDiem_4(resultSet.getFloat("diem_4"));
		hvlh.setGhichu_HVLH(resultSet.getString("ghichu_HVLH"));
		return hvlh;
	}

}
