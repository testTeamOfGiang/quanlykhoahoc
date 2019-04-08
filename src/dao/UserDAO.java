package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.JDBC_Connection;
import entity.User;

public class UserDAO {
	public void changePassword(User user) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "update ACCOUNT set password = ? where username = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, user.getPassword());
		preparedStatement.setString(2, user.getUsername());
		preparedStatement.executeUpdate();
		con.close();
	}

	public int getLevel(User user) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select level from ACCOUNT where username = ? and password = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		ResultSet resultSet = preparedStatement.executeQuery();
		int level = -1;
		while (resultSet.next()) {
			level = resultSet.getInt("level");
		}
		con.close();
		return level;
	}
	
	public boolean checkPassword(User user) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from ACCOUNT where username = ? and password = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean check = false;
		while (resultSet.next()) {
			check = true;
		}
		con.close();
		return check;
	}
}
