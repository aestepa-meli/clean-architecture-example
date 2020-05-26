package com.slalom.example.db.sql;

import com.slalom.example.db.sql.model.UserDb;
import com.slalom.example.domain.entity.User;
import com.slalom.example.usecase.port.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSourceSqlRepository implements UserRepository {
	private Connection con;

	public DataSourceSqlRepository() {
		this.con = DataSourceSqlDocker.getConnection();
	}

	@Override
	public User create(User user) {
		String insert = "INSERT INTO user (email,first_name,last_name,password) VALUES (\"%s\",\"%s\",\"%s\",\"%s\");";
		String SQL_QUERY = String.format(insert, user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
		UserDb userDb = UserDb.from(user);
		try (PreparedStatement pst = con.prepareStatement(SQL_QUERY, new String[]{"id"})) {
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				userDb.setId(Integer.toString(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userDb.toUser();
	}

	@Override
	public Optional<User> findById(String id) {
		String SQL_QUERY = String.format("SELECT * FROM user WHERE id = %s", id);
		UserDb userDb = new UserDb();
		try (
			 PreparedStatement pst = con.prepareStatement(SQL_QUERY); ResultSet rs = pst.executeQuery();) {
			if (rs.next()) {
				userDb.setId(Integer.toString(rs.getInt("id")));
				userDb.setEmail(rs.getString("email"));
				userDb.setFirstName(rs.getString("first_name"));
				userDb.setLastName(rs.getString("last_name"));
				userDb.setPassword(rs.getString("password"));
				userDb.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(userDb.toUser());
	}

	@Override
	public Optional<User> findByEmail(String email) {
		String SQL_QUERY = String.format("SELECT * FROM user WHERE email = \"%s\"", email);
		UserDb userDb = null;
		try (
			 PreparedStatement pst = con.prepareStatement(SQL_QUERY);
			 ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				userDb.setId(Integer.toString(rs.getInt("id")));
				userDb.setEmail(rs.getString("email"));
				userDb.setFirstName(rs.getString("first_name"));
				userDb.setLastName(rs.getString("last_name"));
				userDb.setPassword(rs.getString("password"));
				userDb.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(userDb).map(u -> u.toUser());
	}

	@Override
	public List<User> findAllUsers() {
		String SQL_QUERY = String.format("SELECT * FROM user");
		List<User> userList = new ArrayList<>();

		try (PreparedStatement pst = con.prepareStatement(SQL_QUERY);
			 ResultSet rs = pst.executeQuery();) {
			while(rs.next()) {
				UserDb userDb = new UserDb();
				userDb.setId(Integer.toString(rs.getInt("id")));
				userDb.setEmail(rs.getString("email"));
				userDb.setFirstName(rs.getString("first_name"));
				userDb.setLastName(rs.getString("last_name"));
				userDb.setPassword(rs.getString("password"));
				userDb.setRole(rs.getString("role"));
				userList.add(userDb.toUser());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
