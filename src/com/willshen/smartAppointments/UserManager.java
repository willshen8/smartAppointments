package com.willshen.smartAppointments;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserManager {

    static SessionFactory sessionFactory;

    public UserManager() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            try {
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function saves a newly created user object to the database.
     * @param user This is the object to be added into the database.
     * @return Returns true if the object is added successfully and false otherwise.
     */
    public boolean registerUser(User user){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This function checks to see if a particular user is a validated user by checking whether it exists
     * in database.
     * @param user The user object is the user to be checked for validity.
     * @return True if user a validated user and false otherwise.
     */
    public boolean validateUser(User user){
        Session session = sessionFactory.openSession();
        User user1 = session.get(User.class, user.getEmail());
        try{
            if(user1.getPassword().equals(user.getPassword())){
                return true;
            }
        } catch(Exception e){
        }
        return false;
    }

    /**
     * This function retrieves the user object from database based on the primary id of user's email.
     * @param emailAddress Email address is the primary id of the user object.
     * @return True if user is successfully retrieved and false otherwise.
     */
    public User getUser(String emailAddress) {
        try {
            Session session = sessionFactory.openSession();
            User user2 = session.get(User.class, emailAddress);
            session.close();
            return user2;
        } catch (Exception e) {
            return null;
        }
    }
}