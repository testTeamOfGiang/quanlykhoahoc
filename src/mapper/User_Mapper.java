package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class User_Mapper implements Mapper<User>{

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setLevel(resultSet.getInt("level"));
		return user;
	}

}
