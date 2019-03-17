package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.JDBC_Connection;
import entity.LopCho;

public class LopChoDAO {
	
	public void addLopCho(LopCho lc) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "insert into LOPCHO(id_HV, id_LH, ghichu_LC) values(?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lc.getId_HV());
		preparedStatement.setInt(2, lc.getId_LH());
		preparedStatement.setString(3, lc.getGhichu_LC());
		
		preparedStatement.executeUpdate();
		con.close();
	}
	
}
