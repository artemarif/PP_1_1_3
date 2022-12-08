package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            try {
                connection.setAutoCommit(false);
                statement.execute("CREATE TABLE IF NOT EXISTS Users" +
                        "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(64) NOT NULL," +
                        "lastName VARCHAR(64) NOT NULL," +
                        "age SMALLINT NOT NULL)");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            try {
                connection.setAutoCommit(false);
                statement.execute("DROP TABLE IF EXISTS users");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO Users VALUES(DEFAULT, ?, ?, ?)")) {
            try {
                connection.setAutoCommit(false);
                st.setString(1, name);
                st.setString(2, lastName);
                st.setByte(3, age);
                st.executeUpdate();
                System.out.println("User с именем – " + name + " добавлен в базу данных");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM Users WHERE id=?;")) {
            try {
                connection.setAutoCommit(false);
                st.setLong(1, id);
                st.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement st = connection.createStatement()) {
            try {
                connection.setAutoCommit(false);
                ResultSet rs = st.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    User user = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                    user.setId(rs.getLong("id"));
                    userList.add(user);
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement st = connection.createStatement()) {
            try {
                connection.setAutoCommit(false);
                st.executeUpdate("TRUNCATE TABLE Users;");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
