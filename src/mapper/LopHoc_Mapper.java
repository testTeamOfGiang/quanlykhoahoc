package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Lophoc;

public class LopHoc_Mapper implements Mapper<Lophoc> {

	@Override
	public Lophoc map(ResultSet resultSet) throws SQLException {
		Lophoc lh = new Lophoc();
		lh.setId_LH(resultSet.getInt("id_LH"));
		lh.setId_GV(resultSet.getInt("id_GV"));
		lh.setId_KH(resultSet.getInt("id_KH"));
		lh.setId_PH(resultSet.getInt("id_PH"));
		lh.setNgaybatdau(resultSet.getDate("ngaybaudau"));
		lh.setNgayketthuc(resultSet.getDate("ngayketthuc"));
		return lh;
	}

}
