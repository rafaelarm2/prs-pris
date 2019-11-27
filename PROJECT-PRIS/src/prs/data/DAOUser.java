package prs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import prs.controller.entity.User;

public class DAOUser implements IRepositoryUser {
	private Connection connect;

	public DAOUser() throws SQLException {
		//CREATE A CONNECTION
		connect = ConnectionFactory.getConnection();
	}

	@Override
	public boolean create(User user) {
		try {
			PreparedStatement stmt = connect.prepareStatement(
					"INSERT INTO TB_USER(cpf, password) VALUES(?,?);");
			stmt.setString(1, user.getCpf());
			stmt.setString(2, user.getPassword());

			try {
				stmt.execute();
			} catch (SQLServerException e) {
				return false;
			}
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean isValid(User user) {
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT cpf, password FROM TB_USER"
					+ " WHERE (cpf=? and password=?);");
			stmt.setString(1, user.getCpf());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

}
