package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LichHoc;

public class LichHoc_Mapper implements Mapper<LichHoc> {

	@Override
	public LichHoc map(ResultSet resultSet) throws SQLException {
		LichHoc lih = new LichHoc();
		lih.setId_LIH(resultSet.getInt("id_LIH"));
		lih.setId_LH(resultSet.getInt("id_LH"));
		lih.setThu(resultSet.getString("thu"));
		lih.setTiet(resultSet.getString("tiet"));
		lih.setGhichu_LIH(resultSet.getString("ghichu_LIH"));
		return lih;
	}

}
