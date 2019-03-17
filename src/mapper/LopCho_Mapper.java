package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LopCho;

public class LopCho_Mapper implements Mapper<LopCho> {

	@Override
	public LopCho map(ResultSet resultSet) throws SQLException {
		LopCho lc = new LopCho();
		lc.setId_LH(resultSet.getInt("id_LH"));
		lc.setId_HV(resultSet.getInt("id_HV"));
		lc.setGhichu_LC(resultSet.getString("ghichu_LC"));
		return null;
	}

}
