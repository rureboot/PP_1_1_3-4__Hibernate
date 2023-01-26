package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery("""
                    CREATE TABLE IF NOT EXISTS Users(
                        Id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        Name VARCHAR(45),
                        LastName VARCHAR(45),
                        Age TINYINT)
                    """).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery("DROP TABLE users").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();


            User user = session.get(User.class, id);
            session.remove(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            users = session.createQuery("""
                    select u from User u
                                        """, User.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
