package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {

    static UserService service = new UserServiceImpl();

    public static void main(String[] args) {

        service.createUsersTable();

        service.saveUser("Vladimir", "Burkov", (byte) 22);
        service.saveUser("John", "Doe", (byte) 27);
        service.saveUser("Nick", "Gelevich", (byte) 32);
        service.saveUser("Maxim", "Voronin", (byte) 12);

        List<User> users = service.getAllUsers();
        users.forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();


    }


}

