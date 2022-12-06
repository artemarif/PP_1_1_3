package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("name1", "lastname1", (byte)1);
        us.saveUser("name2", "lastname2", (byte)2);
        us.saveUser("name3", "lastname3", (byte)3);
        us.saveUser("name4", "lastname4", (byte)4);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
