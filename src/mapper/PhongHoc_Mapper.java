package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Phonghoc;

public class PhongHoc_Mapper implements Mapper<Phonghoc> {

	@Override
	public Phonghoc map(ResultSet resultSet) throws SQLException {
		Phonghoc ph = new Phonghoc();
		ph.setId_PH(resultSet.getInt("id_PH"));
		ph.setTen_PH(resultSet.getString("ten_PH"));
		ph.setGhichu_PH(resultSet.getString("ghichu_PH"));
		return ph;
	}

}
