package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LopHoc;

public class LopHoc_Mapper implements Mapper<LopHoc> {

	@Override
	public LopHoc map(ResultSet resultSet) throws SQLException {
		LopHoc lh = new LopHoc();
		lh.setId_LH(resultSet.getInt("id_LH"));
		lh.setId_GV(resultSet.getInt("id_GV"));
		lh.setId_KH(resultSet.getInt("id_KH"));
		lh.setId_PH(resultSet.getInt("id_PH"));
		lh.setTen_LH(resultSet.getString("ten_LH"));
		lh.setNgaybatdau(resultSet.getDate("ngaybatdau"));
		lh.setNgayketthuc(resultSet.getDate("ngayketthuc"));
		lh.setSiso_LH(resultSet.getInt("siso_LH"));
		return lh;
	}

}
