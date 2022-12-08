package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao udi = new UserDaoJDBCImpl();
    public void createUsersTable() throws SQLException {
        udi.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        udi.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        udi.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        udi.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return udi.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        udi.cleanUsersTable();
    }
}
